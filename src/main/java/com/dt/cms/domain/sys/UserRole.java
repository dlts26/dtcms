package com.dt.cms.domain.sys;

import com.dt.cms.domain.BaseDomain;

/**
 * 用户角色
 * 
 * @Author 岳海亮
 * @Mail yhl@feheadline.com
 * @Date 2016年5月5日
 */
public class UserRole extends BaseDomain<UserRole> {

	// Fields
	private static final long serialVersionUID = 1L;
	private Integer id;
	private User user;
	private Role role;

	// Constructors

	/** default constructor */
	public UserRole() {
	}

	/** full constructor */
	public UserRole(User user, Role role) {
		this.user = user;
		this.role = role;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;

	}
}