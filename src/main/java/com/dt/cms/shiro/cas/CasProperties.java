package com.dt.cms.shiro.cas;

import org.springframework.beans.factory.annotation.Value;

/**
 * cas服务器地址配置
 * 
 * @author 岳海亮
 * @date 2017年7月6日
 */
// @Component
public class CasProperties {

	@Value("${cas.server.loginUrl}")
	private String casServerLoginUrl;

	@Value("${cas.server.urlPrefix}")
	private String casServerUrlPrefix;

	@Value("${cas.server.serverName}")
	private String serverName;

	public String getCasServerLoginUrl() {
		return casServerLoginUrl;
	}

	public void setCasServerLoginUrl(String casServerLoginUrl) {
		this.casServerLoginUrl = casServerLoginUrl;
	}

	public String getCasServerUrlPrefix() {
		return casServerUrlPrefix;
	}

	public void setCasServerUrlPrefix(String casServerUrlPrefix) {
		this.casServerUrlPrefix = casServerUrlPrefix;
	}

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

}
