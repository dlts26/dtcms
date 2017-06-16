package com.dt.cms.util;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.jasig.cas.client.util.AssertionHolder;

import com.dt.cms.domain.sys.User;

public class UserUtil {
	/**
	 * 获取当前用户对象shiro
	 * 
	 * @return shirouser
	 */
	public static String getCurrentUserId() {
		String userId = (String) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
		return userId;
	}

	/**
	 * 获取当前用户session
	 * 
	 * @return session
	 */
	public static Session getSession() {
		Session session = SecurityUtils.getSubject().getSession();
		return session;
	}

	/**
	 * 获取当前用户httpsession
	 * 
	 * @return httpsession
	 */
	public static Session getHttpSession() {
		Session session = SecurityUtils.getSubject().getSession();
		return session;
	}

	/**
	 * 获取当前用户对象
	 * 
	 * @return user
	 */
	public static User getCurrentUser() {
		Session session = SecurityUtils.getSubject().getSession();
		if (null != session) {
			return (User) session.getAttribute("user");
		} else {
			return null;
		}

	}
}
