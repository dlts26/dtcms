package com.dt.cms.service.sys;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dt.cms.domain.sys.Permission;
import com.dt.cms.domain.sys.Role;
import com.dt.cms.domain.sys.RolePermission;
import com.dt.cms.mapper.sys.RolePermissionMapper;
import com.dt.cms.service.BaseService;
import com.dt.cms.shiro.cas.UserShiroCasRealm;

/**
 * 角色权限service
 * @author 岳海亮
 * @date 2017年7月12日
 */
@Service
@Transactional(readOnly = true)
public class RolePermissionService extends BaseService<RolePermissionMapper, RolePermission> {

	@Autowired
	private RolePermissionMapper rolePermissionMapper;

	/**
	 * 获取角色权限id集合
	 * 
	 * @param id
	 * @return List
	 */
	public List<Integer> getPermissionIds(Integer roleId) {
		return rolePermissionMapper.findPermissionIds(roleId);
	}

	/**
	 * 修改角色权限
	 * 
	 * @param id
	 * @param oldList
	 * @param newList
	 */
	@Transactional(readOnly = false)
	public void updateRolePermission(Integer id, List<Integer> oldList, List<Integer> newList) {
		// 是否删除
		for (int i = 0, j = oldList.size(); i < j; i++) {
			if (!newList.contains(oldList.get(i))) {
				rolePermissionMapper.deleteRP(id, oldList.get(i));
			}
		}

		// 是否添加
		for (int i = 0, j = newList.size(); i < j; i++) {
			if (!oldList.contains(newList.get(i))) {
				rolePermissionMapper.insertSelective(getRolePermission(id, newList.get(i)));
			}
		}
	}

	/**
	 * 清空该角色用户的权限缓存
	 */
	public void clearUserPermCache(PrincipalCollection pc) {
		RealmSecurityManager securityManager = (RealmSecurityManager) SecurityUtils.getSecurityManager();
		UserShiroCasRealm userRealm = (UserShiroCasRealm) securityManager.getRealms().iterator().next();
		userRealm.clearCachedAuthorizationInfo(pc);
	}

	/**
	 * 构造角色权限对象
	 * 
	 * @param roleId
	 * @param permissionId
	 * @return RolePermission
	 */
	private RolePermission getRolePermission(Integer roleId, Integer permissionId) {
		RolePermission rp = new RolePermission();
		rp.setRole(new Role(roleId));
		rp.setPermission(new Permission(permissionId));
		return rp;
	}

}
