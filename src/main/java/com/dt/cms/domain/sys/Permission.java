package com.dt.cms.domain.sys;

import com.dt.cms.domain.BaseDomain;

public class Permission extends BaseDomain<Permission> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private Integer id;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column permission.PID
	 *
	 * @mbggenerated
	 */
	private Integer pid;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column permission.NAME
	 *
	 * @mbggenerated
	 */
	private String name;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column permission.TYPE
	 *
	 * @mbggenerated
	 */
	private String type;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column permission.SORT
	 *
	 * @mbggenerated
	 */
	private Integer sort;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column permission.URL
	 *
	 * @mbggenerated
	 */
	private String url;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column permission.PERM_CODE
	 *
	 * @mbggenerated
	 */
	private String permCode;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column permission.ICON
	 *
	 * @mbggenerated
	 */
	private String icon;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column permission.STATE
	 *
	 * @mbggenerated
	 */
	private String state;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column permission.DESCRIPTION
	 *
	 * @mbggenerated
	 */
	private String description;

	public Permission() {

	}

	public Permission(Integer pid, String name, String type, String url, String permCode) {
		this.pid = pid;
		this.name = name;
		this.type = type;
		this.url = url;
		this.permCode = permCode;
	}

	public Permission(Integer permissionId) {
		this.id = permissionId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column permission.ID
	 *
	 * @return the value of permission.ID
	 *
	 * @mbggenerated
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column permission.ID
	 *
	 * @param id
	 *            the value for permission.ID
	 *
	 * @mbggenerated
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column permission.PID
	 *
	 * @return the value of permission.PID
	 *
	 * @mbggenerated
	 */
	public Integer getPid() {
		return pid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column permission.PID
	 *
	 * @param pid
	 *            the value for permission.PID
	 *
	 * @mbggenerated
	 */
	public void setPid(Integer pid) {
		this.pid = pid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column permission.NAME
	 *
	 * @return the value of permission.NAME
	 *
	 * @mbggenerated
	 */
	public String getName() {
		return name;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column permission.NAME
	 *
	 * @param name
	 *            the value for permission.NAME
	 *
	 * @mbggenerated
	 */
	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column permission.TYPE
	 *
	 * @return the value of permission.TYPE
	 *
	 * @mbggenerated
	 */
	public String getType() {
		return type;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column permission.TYPE
	 *
	 * @param type
	 *            the value for permission.TYPE
	 *
	 * @mbggenerated
	 */
	public void setType(String type) {
		this.type = type == null ? null : type.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column permission.SORT
	 *
	 * @return the value of permission.SORT
	 *
	 * @mbggenerated
	 */
	public Integer getSort() {
		return sort;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column permission.SORT
	 *
	 * @param sort
	 *            the value for permission.SORT
	 *
	 * @mbggenerated
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column permission.URL
	 *
	 * @return the value of permission.URL
	 *
	 * @mbggenerated
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column permission.URL
	 *
	 * @param url
	 *            the value for permission.URL
	 *
	 * @mbggenerated
	 */
	public void setUrl(String url) {
		this.url = url == null ? null : url.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column permission.PERM_CODE
	 *
	 * @return the value of permission.PERM_CODE
	 *
	 * @mbggenerated
	 */
	public String getPermCode() {
		return permCode;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column permission.PERM_CODE
	 *
	 * @param permCode
	 *            the value for permission.PERM_CODE
	 *
	 * @mbggenerated
	 */
	public void setPermCode(String permCode) {
		this.permCode = permCode == null ? null : permCode.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column permission.ICON
	 *
	 * @return the value of permission.ICON
	 *
	 * @mbggenerated
	 */
	public String getIcon() {
		return icon;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column permission.ICON
	 *
	 * @param icon
	 *            the value for permission.ICON
	 *
	 * @mbggenerated
	 */
	public void setIcon(String icon) {
		this.icon = icon == null ? null : icon.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column permission.STATE
	 *
	 * @return the value of permission.STATE
	 *
	 * @mbggenerated
	 */
	public String getState() {
		return state;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column permission.STATE
	 *
	 * @param state
	 *            the value for permission.STATE
	 *
	 * @mbggenerated
	 */
	public void setState(String state) {
		this.state = state == null ? null : state.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column permission.DESCRIPTION
	 *
	 * @return the value of permission.DESCRIPTION
	 *
	 * @mbggenerated
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column permission.DESCRIPTION
	 *
	 * @param description
	 *            the value for permission.DESCRIPTION
	 *
	 * @mbggenerated
	 */
	public void setDescription(String description) {
		this.description = description == null ? null : description.trim();
	}
}