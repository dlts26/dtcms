package com.dt.cms;

import java.util.Map;

import org.springframework.context.ApplicationContext;
/**
 * spring boot的上下文获取工具类
 * @author 岳海亮
 * @date 2017年7月7日
 */
public class SpringContextUtil {
	private static ApplicationContext applicationContext;

	public static void setApplicationContext(ApplicationContext context) {
		applicationContext = context;
	}

	public static Object getBean(String beanName) {
		return applicationContext.getBean(beanName);
	}

	public static <T> Map<String, T> getBeansOfType(Class<T> paramClass) {
		return applicationContext.getBeansOfType(paramClass);
	}
}