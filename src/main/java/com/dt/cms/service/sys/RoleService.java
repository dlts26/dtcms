package com.dt.cms.service.sys;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dt.cms.domain.sys.Role;
import com.dt.cms.domain.sys.User;
import com.dt.cms.domain.sys.UserRole;
import com.dt.cms.mapper.sys.RoleMapper;
import com.dt.cms.mapper.sys.RolePermissionMapper;
import com.dt.cms.mapper.sys.UserRoleMapper;
import com.dt.cms.service.BaseService;

/**
 * 角色service
 * 
 * @Author 岳海亮
 * @Date 2016年5月9日
 */
@Service
@Transactional(readOnly = true)
public class RoleService extends BaseService<RoleMapper, Role> {

	@Autowired
	private RoleMapper roleMapper;

	@Autowired
	UserRoleMapper userRoleMapper;

	@Autowired
	RolePermissionMapper rolePermissionMapper;

	public List<Role> getRolesByUserId(Integer userId) {
		List<Role> result = roleMapper.findRolesByUserId(userId);
		return result;
	}

	@Transactional(readOnly = false)
	public void delete(final Integer id) {
		userRoleMapper.deleteUR(null, id);
		rolePermissionMapper.deleteRP(id, null);
		super.delete(id);
	}

	@Transactional(readOnly = false)
	public void saveUserAsGuest(String userId) {
		User u = new User();
		u.setId(Integer.valueOf(userId));
		Role r = roleMapper.selectByPrimaryKey(2);
		UserRole ur = new UserRole(u, r);
		userRoleMapper.insertSelective(ur);
	}

}
