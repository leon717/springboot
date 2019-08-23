package com.leo.boot.web.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 使用WebMvcConfigurerAdapter替代WebMvcConfigurer
 * 
 * @author Leo
 * @date 2019/08/23
 */
@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter {
    
    @Autowired
    private MyInterceptor myInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(myInterceptor).addPathPatterns("/**");
    }
}
