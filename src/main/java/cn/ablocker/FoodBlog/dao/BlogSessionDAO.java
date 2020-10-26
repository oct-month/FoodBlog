package cn.ablocker.FoodBlog.dao;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

@Component
public class BlogSessionDAO
{
	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	
	// set("key", "jsfkdjfd", 30, TimeUnit.MINUTE) 设置过期时间为30分钟
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
	
	public void remove(final String key)
	{
		if (exists(key))
			redisTemplate.delete(key);
	}
	
	public boolean exists(final String key)
	{
		return redisTemplate.hasKey(key);
	}
}
