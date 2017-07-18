package com.dt.cms.service.sys;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dt.cms.domain.sys.Organization;
import com.dt.cms.domain.sys.User;
import com.dt.cms.domain.sys.UserOrg;
import com.dt.cms.mapper.sys.UserOrgMapper;
import com.dt.cms.service.BaseService;

/**
 * 用户机构Service
 * 
 * @Author 岳海亮
 * @Date 2016年5月9日
 */
@Service
@Transactional(readOnly = true)
public class UserOrgService extends BaseService<UserOrgMapper, UserOrg> {

	@Autowired
	private UserOrgMapper userOrgMapper;

	/**
	 * 添加修改用户机构
	 * 
	 * @param id
	 * @param oldList
	 * @param newList
	 */
	@Transactional(readOnly = false)
	public void updateUserOrg(Integer userId, List<Integer> newOrgList) {
		// 删除老的机构关系
		userOrgMapper.deleteUO(userId);
		// 添加新的机构关系
		for (Integer newOrgId : newOrgList) {
			UserOrg uo = new UserOrg();
			uo.setUser(new User(userId));
			uo.setOrganization(new Organization(newOrgId));
			userOrgMapper.insertSelective(uo);
		}
	}

	/**
	 * 获取用户拥有机构id集合
	 * 
	 * @param userId
	 * @return 结果集合
	 */
	public List<Integer> getOrgIdList(Integer userId) {
		return userOrgMapper.findOrgIds(userId);
	}

}
