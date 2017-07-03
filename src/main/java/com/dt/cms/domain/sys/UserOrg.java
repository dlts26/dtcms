package com.dt.cms.domain.sys;

import com.dt.cms.domain.BaseEntity;

/**
 * 用户机构model
 * 
 * @Author 岳海亮
 * @Mail yhl@feheadline.com
 * @Date 2016年5月5日
 */

public class UserOrg extends BaseEntity<UserOrg> {

	// Fields

	private static final long serialVersionUID = 1L;
	private Integer id;
	private User user;
	private Organization organization;

	// Constructors

	/** default constructor */
	public UserOrg() {
	}

	/** full constructor */
	public UserOrg(User user, Organization organization) {
		this.user = user;
		this.organization = organization;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

}