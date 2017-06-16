package com.dt.cms.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 定时任务类
 * 
 * @author 岳海亮
 * @date 2017年5月9日
 */
@Component
public class ScheduledTask {

	private static final Logger logger = LoggerFactory.getLogger(ScheduledTask.class);

	/**
	 * 定时加载词库类
	 */
	@Scheduled(initialDelay = 1000 * 60 * 60 * 2, fixedRate = 1000 * 60 * 60 * 2)
	// @Scheduled(initialDelay = 1000 * 60 * 5, fixedRate = 1000 * 60 * 60 * 2)
	public void reloadWords() {
		logger.info("reloadWords start");
	}

}