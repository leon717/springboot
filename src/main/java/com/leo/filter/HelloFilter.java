package com.leo.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.context.annotation.Configuration;

/**
 * 过滤器
 * @author Leonhardt
 *
 */
@Configuration
public class HelloFilter implements Filter{
 
    public HelloFilter() {
       System.out.println("1.创建过滤器实例");
    }

    public void init(FilterConfig arg0) throws ServletException {
       System.out.println("2.过滤器初始化方法");
    }
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
           throws IOException, ServletException {
       System.out.println("3.过滤器业务处理方法");
       //放行到servlet
       chain.doFilter(request, response);
       System.out.println("4.业务处理完回到过滤器");
    }
    
    public void destroy() {
        System.out.println("5.销毁过滤 器实例");
    }
}