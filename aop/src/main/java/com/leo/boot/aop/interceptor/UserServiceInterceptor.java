package com.leo.boot.aop.interceptor;

import com.leo.boot.aop.domain.MockUser;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class UserServiceInterceptor {

    @Around("execution(* com.leo.boot.aop.service.MockUserService.intercept(com.leo.boot.aop.domain.MockUser)) && args(user)")
    public MockUser update(MockUser user) {
        return user.setName("intercepted");
    }

}
