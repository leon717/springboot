package com.leo.boot.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.springframework.stereotype.Component;

@Component
@WebFilter(urlPatterns = "/**", filterName = "MyFilter")
public class MyFilter implements Filter {

    public MyFilter() {
        System.out.println("1.创建过滤器实例");
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
        System.out.println("2.过滤器初始化方法");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException {
        System.out.println("3.过滤器业务处理方法");
        // 放行到servlet
        chain.doFilter(request, response);
        System.out.println("4.业务处理完回到过滤器");
    }

    @Override
    public void destroy() {
        System.out.println("5.销毁过滤器实例");
    }

}
