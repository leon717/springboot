package com.leo.boot.aop.annotation.service;

import org.springframework.stereotype.Service;

import com.leo.boot.aop.annotation.Lock;
import com.leo.boot.aop.annotation.domain.User;

import lombok.SneakyThrows;

@Service
public class MockUserService {

    @Lock(id = "#user.id")
    @SneakyThrows
    public boolean update(User user) {
        Thread.sleep(1000); // mock concurrency
        return true;
    }
    
    @Lock(id = "#user.id")
    @SneakyThrows
    public boolean exception(User user) {
        throw new Exception();
    }
}
