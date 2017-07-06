package com.dt.cms.kafka;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;

/**
 * kafka的配置，
 * @author 岳海亮
 * @date 2017年7月6日
 */
//@EnableKafka
//@Configuration
public class ConsumerConfig {

	@Autowired
	private KafkaProperties kafkaProperties;

	@Value("${kafka.listener.concurrency.news}")
	int concurrency4news;

	@Value("${kafka.listener.concurrency.caifuhao}")
	int concurrency4caifuhao;

	@Bean(name = "newsListenerContainerFactory")
	KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> newsListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
		Map<String, Object> config = kafkaProperties.buildConsumerProperties();
		factory.setConsumerFactory(new DefaultKafkaConsumerFactory<String, String>(config));
		factory.setConcurrency(concurrency4news);
		factory.getContainerProperties().setPollTimeout(3000);
		return factory;
	}

	@Bean(name = "caifuhaoListenerContainerFactory")
	KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> caifuhaoListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
		Map<String, Object> config = kafkaProperties.buildConsumerProperties();
		factory.setConsumerFactory(new DefaultKafkaConsumerFactory<String, String>(config));
		factory.setConcurrency(concurrency4caifuhao);
		factory.getContainerProperties().setPollTimeout(3000);
		return factory;
	}
}