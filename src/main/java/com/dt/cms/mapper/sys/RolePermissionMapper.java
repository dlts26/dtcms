package com.dt.cms.mapper.sys;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dt.cms.domain.sys.RolePermission;
import com.dt.cms.mapper.BaseMapper;

public interface RolePermissionMapper extends BaseMapper<RolePermission> {

	/**
	 * 查询角色拥有的权限
	 * 
	 * @param roleId
	 * @return
	 */
	List<Integer> findPermissionIds(Integer roleId);

	/**
	 * 删除角色权限
	 * 
	 * @param roleId
	 * @param permissionId
	 */
	public void deleteRP(@Param("roleId")Integer roleId, @Param("permissionId")Integer permissionId);
}