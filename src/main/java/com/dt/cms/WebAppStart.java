package com.dt.cms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableAutoConfiguration
public class WebAppStart extends SpringBootServletInitializer {

	private static Logger logger = LoggerFactory.getLogger(WebAppStart.class);

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(WebAppStart.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(WebAppStart.class, args);
		logger.info("Infotag Webapp Start Success");
	}

}