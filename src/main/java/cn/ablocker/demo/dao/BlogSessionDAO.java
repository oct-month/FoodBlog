package cn.ablocker.demo.dao;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

public class BlogSessionDAO
{
	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	
	public void set(final String key, String value, long expireTime, TimeUnit timeUnit)
	{
		ValueOperations<String, String> operations = redisTemplate.opsForValue();
		operations.set(key, value);
		redisTemplate.expire(key, expireTime, timeUnit);
	}
	
	public String get(final String key)
	{
		ValueOperations<String, String> operations = redisTemplate.opsForValue();
		return operations.get(key);
	}
}
