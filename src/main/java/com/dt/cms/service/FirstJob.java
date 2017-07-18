package com.dt.cms.service;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


/**
 * 实现quartz的job接口，定时任务类
 * @author 岳海亮
 * @date 2017年7月18日
 */
@Component
public class FirstJob implements Job {

	private static Logger logger = LoggerFactory.getLogger(FirstJob.class);

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		logger.info("job start");
	}

}
