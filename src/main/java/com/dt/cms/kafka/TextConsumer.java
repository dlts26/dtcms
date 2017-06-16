package com.dt.cms.kafka;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.hadoop.hbase.client.Put;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.dt.cms.hbase.HBaseDAO;

/**
 * 消息消费者
 * 
 * @author 岳海亮
 * @date 2017年5月5日
 */
@Component
public class TextConsumer {

	private static Logger logger = LoggerFactory.getLogger(TextConsumer.class);

	@Autowired
	HBaseDAO hBaseDAO;

	// 线程池，用来限制数据推送的并发数
	ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);

	/**
	 * 资讯文章的消费监听函数
	 * 
	 * @param content
	 */
	@KafkaListener(containerFactory = "newsListenerContainerFactory", topics = "NEWS")
	public void processNewsText(String content) {
	}

	/**
	 * 财富号文章的消费监听处理函数
	 * 
	 * @param content
	 */
	@KafkaListener(containerFactory = "caifuhaoListenerContainerFactory", topics = "CAIFUHAO")
	public void processCaifuhaoText(String content) {

	}

	public <T> void save2Hbase(String rowkey, String optTypeStr, JSONArray tags, T text) {
		Put put = buildPut(rowkey, optTypeStr, tags, text);
		hBaseDAO.put("infoTable", put);
	}

	/**
	 * 生成插入hbase的数据结构
	 * 
	 * @param topicName
	 * @param optType
	 * @param words
	 * @param n
	 * @return
	 */
	private <T> Put buildPut(String rowkey, String optType, JSONArray words, T n) {
		Put put = new Put(rowkey.getBytes());
		put.addColumn("result".getBytes(), "tags".getBytes(), words.toJSONString().getBytes());
		put.addColumn("result".getBytes(), "operatorType".getBytes(), optType.getBytes());
		for (Field f : n.getClass().getDeclaredFields()) {
			String col = f.getName();
			try {
				// 通过反射机制来生成hbase的列名
				Method m = n.getClass().getMethod("get" + col.substring(0, 1).toUpperCase() + col.substring(1));
				Object obj = m.invoke(n);
				put.addColumn("origin".getBytes(), col.getBytes(), String.valueOf(obj).getBytes());

			} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException e) {
				logger.error(ExceptionUtils.getStackTrace(e));
			}
		}
		return put;
	}
}