package com.dt.cms.shiro.redis;

import javax.annotation.Resource;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * 实现CacheManager接口
 * @author 岳海亮
 * @date 2017年7月6日
 */
public class RedisCacheManager implements CacheManager {

	@Resource
	private RedisTemplate<String, Object> redisTemplate;

	@Override
	public <K, V> Cache<K, V> getCache(String name) throws CacheException {
		return new ShiroCache<K, V>(name, redisTemplate);
	}

	public RedisTemplate<String, Object> getRedisTemplate() {
		return redisTemplate;
	}

	public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

}