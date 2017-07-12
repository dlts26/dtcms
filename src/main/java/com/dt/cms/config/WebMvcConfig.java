package com.dt.cms.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSONObject;

/**
 * web mvc配置适配器：配置请求拦截器及视图跳转等信息
 * @author 岳海亮
 * @date 2017年7月4日
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

	private static final Logger logger = LoggerFactory.getLogger(WebMvcConfig.class);

	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new HandlerInterceptorAdapter() {
			long start;

			@Override
			public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
					throws Exception {
				start = System.currentTimeMillis();
				String requestRri = request.getRequestURI();
				String uriPrefix = request.getContextPath();
				String url = StringUtils.substringAfter(requestRri, uriPrefix); // 操作路径
				logger.info("url=" + url);
				return true;
			}

			public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
					Exception ex) throws Exception {
				String requestRri = request.getRequestURI();
				String uriPrefix = request.getContextPath();
				String url = StringUtils.substringAfter(requestRri, uriPrefix);
				String requestParam = JSONObject.toJSONString(request.getParameterMap()); // 请求参数
				logger.info("url=" + url + ",params=" + requestParam + ",costTime="
						+ (System.currentTimeMillis() - start) + "ms");
			}
		}).addPathPatterns("/news/**");
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
        super.addResourceHandlers(registry);
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("system/index");
		registry.addViewController("/login").setViewName("system/login");
	}
}