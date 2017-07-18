package com.dt.cms.service.sys;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dt.cms.domain.sys.Role;
import com.dt.cms.domain.sys.User;
import com.dt.cms.domain.sys.UserRole;
import com.dt.cms.mapper.sys.UserRoleMapper;
import com.dt.cms.service.BaseService;

/**
 * 用户角色service
 * 
 * @Author 岳海亮
 * @Date 2016年5月9日
 */
@Service
@Transactional(readOnly = true)
public class UserRoleService extends BaseService<UserRoleMapper, UserRole> {

	@Autowired
	private UserRoleMapper userRoleMapper;

	/**
	 * 添加修改用户角色
	 * 
	 * @param id
	 * @param oldList
	 * @param newList
	 */
	@Transactional(readOnly = false)
	public void updateUserRole(Integer userId, List<Integer> oldList, List<Integer> newList) {
		// 是否删除
		for (int i = 0, j = oldList.size(); i < j; i++) {
			if (!newList.contains(oldList.get(i))) {
				userRoleMapper.deleteUR(userId, oldList.get(i));
			}
		}

		// 是否添加
		for (int m = 0, n = newList.size(); m < n; m++) {
			if (!oldList.contains(newList.get(m))) {
				userRoleMapper.insertSelective(getUserRole(userId, newList.get(m)));
			}
		}
	}

	/**
	 * 构建UserRole
	 * 
	 * @param userId
	 * @param roleId
	 * @return UserRole
	 */
	private UserRole getUserRole(Integer userId, Integer roleId) {
		UserRole ur = new UserRole();
		ur.setUser(new User(userId));
		ur.setRole(new Role(roleId));
		return ur;
	}

	/**
	 * 获取用户拥有角色id集合
	 * 
	 * @param userId
	 * @return 结果集合
	 */
	public List<Integer> getRoleIdList(Integer userId) {
		return userRoleMapper.findRoleIds(userId);
	}
}
