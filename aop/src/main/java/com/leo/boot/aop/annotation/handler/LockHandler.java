package com.leo.boot.aop.annotation.handler;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.leo.boot.aop.annotation.Lock;
import com.leo.boot.aop.lock.RedisLock;

@Component
@Aspect
public class LockHandler {

    @Autowired
    private RedisLock redisLock;

    @Around("@annotation(com.leo.boot.aop.annotation.Lock)")
    public Object handle(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = ((MethodSignature)joinPoint.getSignature());
        Lock lock = methodSignature.getMethod().getAnnotation(Lock.class);

        String lockId = SpelContext.of(methodSignature, joinPoint.getArgs()).getValue(lock.id());
        String lockKey = String.format(lock.key(), lockId);
        String lockTS = String.valueOf(System.currentTimeMillis() + lock.timeout());
        int retryTimes = lock.retryTimes();

        if (redisLock.lock(lockKey, lockTS, retryTimes)) {
            try {
                return joinPoint.proceed();
            } finally {
                redisLock.unlock(lockKey, lockTS);
            }
        } else {
            throw new IllegalStateException("unable to acquire lock of obj");
        }
    }

}
