package com.leo.boot.aop.service;

import com.leo.boot.aop.annotation.Lock;
import com.leo.boot.aop.domain.MockUser;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

@Service
public class MockUserService {

    @Lock(id = "#user.id")
    @SneakyThrows
    public boolean update(MockUser user) {
        Thread.sleep(1000); // mock concurrency
        return true;
    }
    
    @Lock(id = "#user.id")
    @SneakyThrows
    public boolean exception(MockUser user) {
        throw new Exception();
    }
}
