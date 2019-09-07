package com.leo.boot.cache;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.leo.boot.cache.domain.Info;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTemplateTest {

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	@Test
	public void testValueOps() {
		String key = "key";
		String value = "value";
		redisTemplate.delete(key);
		redisTemplate.opsForValue().set(key, value);
		Object result = redisTemplate.opsForValue().get(key);
		Assert.assertEquals(value, result);
	}
	
	@Test
	public void testObjOps() {
		String key = "info";
		Info value = Info.builder().id("123").name("张三").build();
		redisTemplate.delete(key);
		redisTemplate.opsForValue().set(key, value);
		Object result = redisTemplate.opsForValue().get(key);
		Assert.assertEquals(value, result);
	}

}
