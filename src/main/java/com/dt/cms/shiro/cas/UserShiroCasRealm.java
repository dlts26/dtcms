package com.dt.cms.shiro.cas;

import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cas.CasRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dt.cms.domain.sys.Permission;
import com.dt.cms.domain.sys.Role;
import com.dt.cms.service.sys.PermissionService;
import com.dt.cms.service.sys.RoleService;

/**
 * 验证用户登录
 * 
 * @author Administrator
 */
//@Component("userShiroCasRealm")
public class UserShiroCasRealm extends CasRealm {

	@Autowired
	protected PermissionService permissionService;

	@Autowired
	protected RoleService roleService;

	@Autowired
	CasProperties casProperties;

	@PostConstruct
	public void initProperty() {
		setCasServerUrlPrefix(casProperties.getCasServerUrlPrefix());
		// 客户端回调地址
		setCasService(casProperties.getServerName() + "/shiro-cas");
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		AuthenticationInfo ai = super.doGetAuthenticationInfo(token);
		if (ai != null) {
			PrincipalCollection pc = ai.getPrincipals();
			String userId = (String) pc.getPrimaryPrincipal();
			List<Role> ur = roleService.getRolesByUserId(Integer.valueOf(userId));
			if (ur == null || ur.size() == 0)
				roleService.saveUserAsGuest(userId);
		}
		return ai;
	}

	/**
	 * 权限认证，为当前登录的Subject授予角色和权限
	 * 
	 * @see 经测试：本例中该方法的调用时机为需授权资源被访问时
	 * @see 经测试：并且每次访问需授权资源时都会执行该方法中的逻辑，这表明本例中默认并未启用AuthorizationCache
	 * @see 经测试：如果连续访问同一个URL（比如刷新），该方法不会被重复调用，Shiro有一个时间间隔（也就是cache时间，在ehcache-shiro.xml中配置），超过这个时间间隔再刷新页面，该方法会被执行
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String userId = (String) principals.getPrimaryPrincipal();
		// 把principals放session中 key=userId value=principals
		SecurityUtils.getSubject().getSession().setAttribute(userId, SecurityUtils.getSubject().getPrincipals());
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		// 赋予角色
		for (Role role : roleService.getRolesByUserId(Integer.valueOf(userId))) {
			info.addRole(role.getName());
		}
		// 赋予权限
		for (Permission permission : permissionService.getPermissionsByUserId(Integer.valueOf(userId))) {
			if (StringUtils.isNotBlank(permission.getPermCode()))
				info.addStringPermission(permission.getPermCode());
		}

		// 设置登录次数、时间
		// updateUserLogin(Integer.valueOf(userId));
		return info;
	}

	@Override
	public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
		super.clearCachedAuthorizationInfo(principals);
	}

	@Override
	public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
		super.clearCachedAuthenticationInfo(principals);
	}

	@Override
	public void clearCache(PrincipalCollection principals) {
		super.clearCache(principals);
	}

	public void clearAllCachedAuthorizationInfo() {
		getAuthorizationCache().clear();
	}

	public void clearAllCachedAuthenticationInfo() {
		getAuthenticationCache().clear();
	}

	public void clearAllCache() {
		clearAllCachedAuthenticationInfo();
		clearAllCachedAuthorizationInfo();
	}
}