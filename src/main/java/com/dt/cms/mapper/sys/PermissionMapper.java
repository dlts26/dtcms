package com.dt.cms.mapper.sys;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dt.cms.domain.sys.Permission;
import com.dt.cms.entity.Page;
import com.dt.cms.mapper.BaseMapper;

public interface PermissionMapper extends BaseMapper<Permission> {

	/**
	 * 查询用户拥有的权限
	* @param userId
	* @param page
	* @return
	 */
	List<Permission> findPermissions(@Param("userId") Integer userId, @Param("page") Page<Permission> page);

	/**
	 * 查询用户拥有的菜单权限，当userid为null时查询所有的按钮:
	* @param userId
	* @return
	 */
	List<Permission> findMenus(Integer userId);


	/**
	 * 查询菜单下的操作权限
	* @param pid
	* @return
	 */
	List<Permission> findMenuOperation(Integer pid);

	List<Permission> findPermissionsByRoleName(String roleName);

	List<Permission> findByParentId(Integer pid);

}