package com.dt.cms.service.sys;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dt.cms.domain.sys.User;
import com.dt.cms.mapper.sys.UserMapper;
import com.dt.cms.service.BaseService;
import com.dt.cms.util.DateUtils;
import com.dt.cms.util.security.Digests;
import com.dt.cms.util.security.Encodes;

/**
 * 用户service
 * 
 * @Author 岳海亮
 * @Date 2016年5月9日
 */
@Service
@Transactional(readOnly = true)
public class UserService extends BaseService<UserMapper, User> {

	private static final Logger logger = LoggerFactory.getLogger(UserService.class);

	/** 加密方法 */
	public static final String HASH_ALGORITHM = "SHA-1";
	public static final int HASH_INTERATIONS = 1024;
	private static final int SALT_SIZE = 8; // 盐长度

	@Autowired
	private UserMapper userMapper;

	/**
	 * 保存用户
	 * 
	 * @param user
	 */
	@Transactional(readOnly = false)
	public void save(User user) {
		try {
			entryptPassword(user);
			user.setCreateTime(DateUtils.getSysTimestamp());
			userMapper.insertSelective(user);
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}

	}

	/**
	 * 修改密码
	 * 
	 * @param user
	 */
	@Transactional(readOnly = false)
	public void updatePwd(User user) {
		entryptPassword(user);
		userMapper.updateByPrimaryKeySelective(user);
	}

	/**
	 * 删除用户
	 * 
	 * @param id
	 */
	@Transactional(readOnly = false)
	public void delete(Integer id) {
		if (!isSupervisor(id))
			userMapper.deleteByPrimaryKey(id);
	}

	/**
	 * 按登录名查询用户
	 * 
	 * @param loginName
	 * @return 用户对象
	 */
	public User getUser(String loginName) {
		return userMapper.findByLoginName(loginName);
	}

	/**
	 * 判断是否超级管理员
	 * 
	 * @param id
	 * @return boolean
	 */
	private boolean isSupervisor(Integer id) {
		return id == 1;
	}

	/**
	 * 设定安全的密码，生成随机的salt并经过1024次 sha-1 hash
	 */
	private void entryptPassword(User user) {
		byte[] salt = Digests.generateSalt(SALT_SIZE);
		user.setSalt(Encodes.encodeHex(salt));

		byte[] hashPassword = Digests.sha1(user.getPlainPassword().getBytes(), salt, HASH_INTERATIONS);
		user.setPassword(Encodes.encodeHex(hashPassword));
	}

	/**
	 * 验证原密码是否正确
	 * 
	 * @param user
	 * @param oldPwd
	 * @return
	 */
	public boolean checkPassword(User user, String oldPassword) {
		byte[] salt = Encodes.decodeHex(user.getSalt());
		byte[] hashPassword = Digests.sha1(oldPassword.getBytes(), salt, HASH_INTERATIONS);
		if (user.getPassword().equals(Encodes.encodeHex(hashPassword))) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 修改用户登录
	 * 
	 * @param user
	 */
	@Transactional(readOnly = false)
	public void updateUserLogin(User user) {
		user.setLoginCount((user.getLoginCount() == null ? 0 : user.getLoginCount()) + 1);
		user.setPreviousVisit(user.getLastVisit());
		user.setLastVisit(DateUtils.getSysTimestamp());
		update(user);
	}

}
