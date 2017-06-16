package com.dt.cms.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSONObject;

@Configuration
public class WebMvcConfigurer extends WebMvcConfigurerAdapter {

	private static final Logger logger = LoggerFactory.getLogger(WebMvcConfigurer.class);

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
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/test").setViewName("public/test");
		registry.addViewController("/").setViewName("system/index");
	}
}