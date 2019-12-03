package com.leo.boot.aop.lock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class RedisLock {

    public static final String COMMON_LOCK_KEY = "common:lock.obj:%s";
    public static final long COMMON_TIME_OUT = 3000L;

    @Autowired
    private StringRedisTemplate redisTemplate;

    public boolean lock(String key, String value) {
        if (redisTemplate.opsForValue().setIfAbsent(key, value)) {
            return true;
        }

        String currentValue = redisTemplate.opsForValue().get(key);
        if (!StringUtils.isEmpty(currentValue) && System.currentTimeMillis() > Long.parseLong(currentValue)) {
            String oldValue = redisTemplate.opsForValue().getAndSet(key, value);
            if (!StringUtils.isEmpty(oldValue) && oldValue.equals(currentValue)) {
                return true;
            }
        }
        return false;
    }

    public boolean lock(String key, String value, int retryTimes) {
        for (int i = 0; i <= retryTimes; i++) {
            if (lock(key, value)) {
                return true;
            } else {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    log.error("reject to acquire redis lock of obj {}", key);
                    return false;
                }
            }
        }
        log.warn("unable to acquire lock of obj {} after {} times retry", key, retryTimes);
        return false;
    }

    public void unlock(String key, String value) {
        String currentValue = redisTemplate.opsForValue().get(key);
        if (!StringUtils.isEmpty(currentValue) && currentValue.equals(value)) {
            redisTemplate.opsForValue().getOperations().delete(key);
        }
    }

}
