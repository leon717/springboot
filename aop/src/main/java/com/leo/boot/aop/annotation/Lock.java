package com.leo.boot.aop.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.leo.boot.aop.lock.RedisLock;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Lock {

	String id(); // use SpEL expression

	String key() default RedisLock.COMMON_LOCK_KEY;

	long timeout() default RedisLock.COMMON_TIME_OUT;

	int retryTimes() default 0;

}
