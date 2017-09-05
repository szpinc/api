package org.szpinc.api.cache;

import java.lang.reflect.Method;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * Created by Ghost Dog on 2017/8/25.
 */

@Configuration
@EnableCaching
public class RedisCacheConfig extends CachingConfigurerSupport {

	private volatile JedisConnectionFactory jedisConnectionFactory;

	private volatile RedisTemplate<String, String> redisTemplate;

	private volatile RedisCacheManager redisCacheManager;

	public RedisCacheConfig() {
		super();
	}

	public RedisCacheConfig(JedisConnectionFactory jedisConnectionFactory, RedisTemplate<String, String> redisTemplate,
			RedisCacheManager redisCacheManager) {
		this.jedisConnectionFactory = jedisConnectionFactory;
		this.redisTemplate = redisTemplate;
		this.redisCacheManager = redisCacheManager;
	}
	
	public JedisConnectionFactory getJedisConnectionFactory() {
		return jedisConnectionFactory;
	}
	
	public RedisTemplate<String, String> redisTemplate (RedisConnectionFactory cf) {
		return redisTemplate ;
	}
	
	public CacheManager cacheManager (RedisTemplate<?, ?> redisTemplate) {
		return redisCacheManager ;
	}
	
	
	

	@Bean
	public KeyGenerator customKeyGenerator() {
		return new KeyGenerator() {
			
			@Override
			public Object generate(Object target, Method method, Object... params) {
				StringBuilder sb = new StringBuilder();
				sb.append(target.getClass().getName());
				sb.append(method.getName());
				for (Object object : params) {
					sb.append(object.toString());
				}
				return sb.toString();
			}
		};
	}
}
