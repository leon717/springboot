package com.leo.boot.cache.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.leo.boot.cache.domain.Info;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BusinessServiceTest {

    @Autowired
    private BusinessService businessService;
    
    @Test
    public void test() {
        testSet();
        testGet();
    }

    public void testSet() {
        Info info = new Info().id("123").name("张三");
        businessService.set(info);
    }

    public void testGet() {
        Info info = new Info().id("123").name("张三");
        Info result = (Info)businessService.get("123");
        assertEquals(info, result);
    }

}
