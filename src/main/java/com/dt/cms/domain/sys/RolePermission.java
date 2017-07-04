package com.dt.cms.domain.sys;

import com.dt.cms.domain.BaseDomain;

/**
 * 
 * @Author 岳海亮
 * @Mail yhl@feheadline.com
 * @Date 2016年5月5日
 */
public class RolePermission extends BaseDomain<RolePermission> {

	// Fields
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Permission permission;
	private Role role;

	// Constructors

	/** default constructor */
	public RolePermission() {
	}

	/** full constructor */
	public RolePermission(Permission permission, Role role) {
		this.permission = permission;
		this.role = role;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public Permission getPermission() {
		return this.permission;
	}

	public void setPermission(Permission permission) {
		this.permission = permission;
	}

	
	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}