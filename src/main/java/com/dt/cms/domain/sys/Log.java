package com.dt.cms.domain.sys;

import java.sql.Timestamp;
import java.util.Date;

import com.dt.cms.domain.BaseDomain;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 日志entity
 * @author 岳海亮
 * @date 2017年7月3日
 */

public class Log extends BaseDomain<Log> {

	private static final long serialVersionUID = 1L;
	// Fields
	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	private String operationCode;
	private String creater;
	private Date createDate;
	private Integer type;
	private String os;
	private String browser;
	private String ip;
	private String mac;
	private Integer executeTime;
	private String description;
	private String requestParam;

	// Constructors

	/** default constructor */
	public Log() {
	}

	/** minimal constructor */
	public Log(String operationCode, Timestamp createDate) {
		this.operationCode = operationCode;
		this.createDate = createDate;
	}

	/** full constructor */
	public Log(String operationCode, String creater, Timestamp createDate, Integer type, String os, String browser,
			String ip, String mac, Integer executeTime, String description, String requestParam) {
		this.operationCode = operationCode;
		this.creater = creater;
		this.createDate = createDate;
		this.type = type;
		this.os = os;
		this.browser = browser;
		this.ip = ip;
		this.mac = mac;
		this.executeTime = executeTime;
		this.description = description;
		this.requestParam = requestParam;
	}

	public String getOperationCode() {
		return operationCode;
	}

	public void setOperationCode(String operationCode) {
		this.operationCode = operationCode;
	}

	public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getBrowser() {
		return browser;
	}

	public void setBrowser(String browser) {
		this.browser = browser;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public Integer getExecuteTime() {
		return executeTime;
	}

	public void setExecuteTime(Integer executeTime) {
		this.executeTime = executeTime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRequestParam() {
		return requestParam;
	}

	public void setRequestParam(String requestParam) {
		this.requestParam = requestParam;
	}

}