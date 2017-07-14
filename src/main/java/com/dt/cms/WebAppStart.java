package com.dt.cms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * spring boot 启动函数
 * 
 * @author 岳海亮
 * @date 2017年7月4日
 */
@SpringBootApplication
@EnableScheduling
@EnableAutoConfiguration
public class WebAppStart extends SpringBootServletInitializer {

	private static Logger logger = LoggerFactory.getLogger(WebAppStart.class);

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		SpringApplicationBuilder result = application.sources(WebAppStart.class);
		SpringContextUtil.setApplicationContext(application.context());
		return result;
	}

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(WebAppStart.class, args);
		SpringContextUtil.setApplicationContext(context);
		logger.info("Infotag Webapp Start Success");

	}

}