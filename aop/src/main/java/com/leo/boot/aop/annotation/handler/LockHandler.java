package com.leo.boot.aop.annotation.handler;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import com.leo.boot.aop.annotation.Lock;
import com.leo.boot.aop.lock.RedisLock;

@Component
@Aspect
public class LockHandler {
	
	@Autowired
	private RedisLock redisLock;
	
	private final ExpressionParser expressionParser = new SpelExpressionParser();
	
	private final ParameterNameDiscoverer parameterNameDiscoverer = new DefaultParameterNameDiscoverer();

	@Around("@annotation(com.leo.boot.aop.annotation.Lock)")
	public Object handle(ProceedingJoinPoint joinPoint) throws Throwable {
		Method method = ((MethodSignature)joinPoint.getSignature()).getMethod();
		String[] argNames = parameterNameDiscoverer.getParameterNames(method);
		Object[] args = joinPoint.getArgs();
		
		StandardEvaluationContext context = new StandardEvaluationContext(joinPoint);
		for (int i = 0; i < args.length; i++) {
			context.setVariable(argNames[i], args[i]);
		}
		
		Lock lock = method.getAnnotation(Lock.class);
        String lockId = expressionParser.parseExpression(lock.id()).getValue(context, String.class);
        
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
