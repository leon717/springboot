package com.leo.boot.aop.annotation;

import static org.junit.Assert.assertTrue;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.leo.boot.aop.domain.MockUser;
import com.leo.boot.aop.service.MockUserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LockTest {

    @Autowired
    private MockUserService mockUserService;

    private ExecutorService executor = Executors.newFixedThreadPool(2);

    @Test(expected = ExecutionException.class)
    public void test__update_user_when_concurrent_with_no_timeout_throw_exception() throws Exception {
        final MockUser user = new MockUser().setId("123456").setName("张三");
        Future<Boolean> result1 = executor.submit(() -> mockUserService.update(user));
        Future<Boolean> result2 = executor.submit(() -> mockUserService.update(user));
        result1.get();
        result2.get();
    }

    @Test
    public void test_update_user_when_after_exception_than_true() throws Exception {
        final MockUser user = new MockUser().setId("123456").setName("张三");
        try {
            mockUserService.exception(user);
        } catch (Exception e) {
        }
        boolean result = mockUserService.update(user);
        assertTrue(result);
    }

}
