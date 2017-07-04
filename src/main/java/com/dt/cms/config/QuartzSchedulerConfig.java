package com.dt.cms.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

@Configuration
public class QuartzSchedulerConfig {

	@Autowired
	private DataSource dataSource;

	@Bean("quatzScheduler")
	public SchedulerFactoryBean schedulerFactory() {
		SchedulerFactoryBean bean = new SchedulerFactoryBean();
		bean.setDataSource(dataSource);
		bean.setApplicationContextSchedulerContextKey("applicationContextKey");
		bean.setConfigLocation(new ClassPathResource("application.properties"));
		return bean;
	}
}