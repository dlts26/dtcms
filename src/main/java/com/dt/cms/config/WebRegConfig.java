package com.dt.cms.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.gonvan.kaptcha.servlet.KaptchaServlet;

/**
 * Servlet、Filter、Listener注册
 * 
 * @author 岳海亮
 * @date 2017年7月6日
 */
@Configuration
public class WebRegConfig {

	/************************
	 * Servlet Registration
	 ************************/
	/**
	 * 用来生成验证码的servlet
	 * 
	 * @return
	 */
	@Bean
	public KaptchaServlet kaptchaServlet() {
		return new KaptchaServlet();
	}

	/**
	 * 把验证码servlet进行注册
	 * 
	 * @param kaptchaServlet
	 * @return
	 */
	@Bean
	public ServletRegistrationBean testServletRegistrationBean(KaptchaServlet kaptchaServlet) {
		ServletRegistrationBean registration = new ServletRegistrationBean(kaptchaServlet);
		registration.setEnabled(true);
		// 更多参数配置，参见https://my.oschina.net/wca/blog/1186831
		registration.addInitParameter("kaptcha.textproducer.char.string", "1234567890");
		registration.addInitParameter("kaptcha.textproducer.char.length", "1");
		registration.addUrlMappings("/images/kaptcha.jpg");
		return registration;
	}

	/************************
	 * Filter Registration
	 ************************/

//	@Bean
//	public FilterRegistrationBean indexFilterRegistration() {
//		FilterRegistrationBean registration = new FilterRegistrationBean(new IndexFilter());
//		registration.addUrlPatterns("/");
//		return registration;
//	}

	/************************
	 * Listener Registration
	 ************************/
//	@Bean
//	public ServletListenerRegistrationBean servletListenerRegistrationBean() {
//		ServletListenerRegistrationBean servletListenerRegistrationBean = new ServletListenerRegistrationBean();
//		servletListenerRegistrationBean.setListener(new IndexListener());
//		return servletListenerRegistrationBean;
//	}
}