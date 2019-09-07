package com.leo.boot.aop.annotation;

import java.util.stream.Stream;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import com.leo.boot.aop.annotation.Auth.RoleType;

@Component
@Aspect
public class AuthHandler {

	@Before("@annotation(auth)")
	public void handle(JoinPoint joinPoint, Auth auth) {
		if (joinPoint.getArgs() == null) {
			throw new RuntimeException("401 Unauthorized");
		}
		RoleType roleType = (RoleType) Stream.of(joinPoint.getArgs()).filter(a -> a.getClass().equals(RoleType.class))
				.findFirst().orElse(null);
		if (roleType == null || !Stream.of(auth.value()).anyMatch(roleType::equals)) {
			throw new RuntimeException("401 Unauthorized");
		}
	}

}
