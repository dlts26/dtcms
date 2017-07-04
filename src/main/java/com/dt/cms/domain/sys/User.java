package com.dt.cms.domain.sys;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import com.dt.cms.domain.BaseDomain;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class User extends BaseDomain<User> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Fields
	private Integer id;
	private String loginName;
	private String name;
	private String password;
	private String plainPassword;
	private String salt;
	private String icon;
	private String state;
	private String description;
	private Integer loginCount;
	private Timestamp previousVisit;
	private Timestamp lastVisit;
	private String delFlag;
	@JsonIgnore
	private Set<UserRole> userRoles = new HashSet<UserRole>(0);

	public User() {
	}

	public User(Integer id) {
		this.id = id;
	}

	public User(String loginName, String name, String password) {
		this.loginName = loginName;
		this.name = name;
		this.password = password;
	}

	/** full constructor */
	public User(String loginName, String name, String password, String salt, String icon, String state,
			String description, Integer loginCount, Timestamp previousVisit, Timestamp lastVisit, String delFlag,
			Set<UserRole> userRoles) {
		this.loginName = loginName;
		this.name = name;
		this.password = password;
		this.salt = salt;
		this.icon = icon;
		this.state = state;
		this.description = description;
		this.loginCount = loginCount;
		this.previousVisit = previousVisit;
		this.lastVisit = lastVisit;
		this.delFlag = delFlag;
		this.userRoles = userRoles;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPlainPassword() {
		return plainPassword;
	}

	public void setPlainPassword(String plainPassword) {
		this.plainPassword = plainPassword;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getLoginCount() {
		return loginCount;
	}

	public void setLoginCount(Integer loginCount) {
		this.loginCount = loginCount;
	}

	public Timestamp getPreviousVisit() {
		return previousVisit;
	}

	public void setPreviousVisit(Timestamp previousVisit) {
		this.previousVisit = previousVisit;
	}

	public Timestamp getLastVisit() {
		return lastVisit;
	}

	public void setLastVisit(Timestamp lastVisit) {
		this.lastVisit = lastVisit;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	public Set<UserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Set<UserRole> userRoles) {
		this.userRoles = userRoles;
	}
}