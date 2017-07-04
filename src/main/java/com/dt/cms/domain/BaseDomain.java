package com.dt.cms.domain;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlTransient;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.dt.cms.domain.sys.User;
import com.dt.cms.util.UserUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * 基础域定义，封装一些公用信息
 * @author 岳海亮
 * @date 2017年7月4日
 * @param <T>
 */
public class BaseDomain<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	
	/**
	 * 当前用户
	 */
	protected User currentUser;

	protected String remarks; // 备注
	protected User createBy; // 创建者
	protected Date createTime; // 创建日期
	protected User updateBy; // 更新者
	protected Date updateTime; // 更新日期
	protected String valid; // 删除标记（0：正常；1：删除；2：审核）

	public BaseDomain() {

	}

	@JsonIgnore
	@XmlTransient
	public User getCurrentUser() {
		if (currentUser == null) {
			currentUser = UserUtil.getCurrentUser();
		}
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public User getCreateBy() {
		return createBy;
	}

	public void setCreateBy(User createBy) {
		this.createBy = createBy;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public User getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(User updateBy) {
		this.updateBy = updateBy;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getValid() {
		return valid;
	}

	public void setValid(String valid) {
		this.valid = valid;
	}
}