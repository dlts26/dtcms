package com.dt.cms.shiro;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.cas.CasFilter;
import org.apache.shiro.cas.CasSubjectFactory;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.DelegatingFilterProxy;

import com.dt.cms.shiro.cas.CasProperties;
import com.dt.cms.shiro.cas.UserShiroCasRealm;

//@Configuration
public class ShiroConfiguration {

	@Bean
	public EhCacheManager getEhCacheManager() {
		EhCacheManager em = new EhCacheManager();
		em.setCacheManagerConfigFile("classpath:ehcache.xml");
		return em;
	}

	@Bean(name = "userShiroCasRealm")
	public UserShiroRealm myShiroCasRealm(EhCacheManager cacheManager) {
		UserShiroRealm realm = new UserShiroRealm();
		realm.setCacheManager(cacheManager);
		return realm;
	}

	/**
	 * 注册DelegatingFilterProxy（Shiro）
	 *
	 * @param dispatcherServlet
	 * @return
	 * @author SHANHY
	 * @create 2016年1月13日
	 */
	@Bean
	public FilterRegistrationBean filterRegistrationBean() {
		FilterRegistrationBean filterRegistration = new FilterRegistrationBean();
		filterRegistration.setFilter(new DelegatingFilterProxy("shiroFilter"));
		// 该值缺省为false,表示生命周期由SpringApplicationContext管理,设置为true则表示由ServletContainer管理
		filterRegistration.addInitParameter("targetFilterLifecycle", "true");
		filterRegistration.setEnabled(true);
		filterRegistration.addUrlPatterns("/*");
		return filterRegistration;
	}

	@Bean(name = "lifecycleBeanPostProcessor")
	public LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}

	@Bean
	public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
		DefaultAdvisorAutoProxyCreator daap = new DefaultAdvisorAutoProxyCreator();
		daap.setProxyTargetClass(true);
		return daap;
	}

	@Bean(name = "securityManager")
	public DefaultWebSecurityManager getDefaultWebSecurityManager(UserShiroCasRealm userShiroCasRealm) {
		DefaultWebSecurityManager dwsm = new DefaultWebSecurityManager();
		dwsm.setRealm(userShiroCasRealm);
		// 用户授权/认证信息Cache, 采用EhCache
		dwsm.setCacheManager(getEhCacheManager());
		// 指定 SubjectFactory
		dwsm.setSubjectFactory(new CasSubjectFactory());
		return dwsm;
	}

	@Bean
	public AuthorizationAttributeSourceAdvisor getAuthorizationAttributeSourceAdvisor(
			DefaultWebSecurityManager securityManager) {
		AuthorizationAttributeSourceAdvisor aasa = new AuthorizationAttributeSourceAdvisor();
		aasa.setSecurityManager(securityManager);
		return aasa;
	}

	/**
	 * 加载shiroFilter权限控制规则（可以从数据库读取然后配置）
	 * 
	 * @param shiroFilterFactoryBean
	 */
	private void loadShiroFilterChain(ShiroFilterFactoryBean shiroFilterFactoryBean, CasFilter casFilter) {
		// 添加casFilter到shiroFilter中
		Map<String, Filter> filters = new HashMap<>();
		// filters.put("casFilter", casFilter);
		// shiroFilterFactoryBean.setFilters(filters);
		/////////////////////// 下面这些规则配置可以配置到配置文件中 ///////////////////////
		Map<String, String> chains = new LinkedHashMap<String, String>();
		// 添加cas过滤器
		// chains.put("/shiro-cas", "casFilter");// shiro集成cas后，首先添加该规则

		// 新闻接口
		chains.put("/news/**", "anon");

		// 开放页面
		chains.put("/main/**", "anon");

		// 静态资源匿名访问
		chains.put("/base/**", "anon");
		chains.put("/css/**", "anon");
		chains.put("/images/**", "anon");
		chains.put("/js/**", "anon");
		chains.put("/layer/**", "anon");
		chains.put("/plugins/**", "anon");

		// 其他需要sso登陆
		chains.put("/**", "authc");
		shiroFilterFactoryBean.setFilterChainDefinitionMap(chains);
	}

	/**
	 * Cas过滤器
	 * 
	 * @param casProperties
	 * @return
	 */

	// @Bean(name = "casFilter")
	// public CasFilter getCasFilter(@Autowired CasProperties casProperties) {
	// CasFilter casFilter = new CasFilter();
	// casFilter.setName("casFilter");
	// casFilter.setEnabled(true);
	// // 登录失败后跳转的URL，也就是 Shiro 执行 CasRealm 的
	// casFilter.setFailureUrl(
	// casProperties.getCasServerLoginUrl() + "?service=" +
	// casProperties.getServerName() + "/");// 再打开登录页面
	// casFilter.setSuccessUrl("/");
	// return casFilter;
	// }

	/**
	 * ShiroFilter<br/>
	 * 注意这里参数中的 StudentService 和 IScoreDao 只是一个例子，因为我们在这里可以用这样的方式获取到相关访问数据库的对象，
	 * 然后读取数据库相关配置，配置到 shiroFilterFactoryBean 的访问规则中。实际项目中，请使用自己的Service来处理业务逻辑。
	 *
	 * @param myShiroCasRealm
	 * @return
	 */
	@Bean(name = "shiroFilter")
	public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager securityManager,
			CasFilter casFilter, @Autowired CasProperties casProperties) {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		// 必须设置 SecurityManager
		shiroFilterFactoryBean.setSecurityManager(securityManager);

		shiroFilterFactoryBean.setLoginUrl(
				casProperties.getCasServerLoginUrl() + "?service=" + casProperties.getServerName() + "/shiro-cas");
		shiroFilterFactoryBean.setUnauthorizedUrl("/403");
		loadShiroFilterChain(shiroFilterFactoryBean, casFilter);
		return shiroFilterFactoryBean;
	}

}