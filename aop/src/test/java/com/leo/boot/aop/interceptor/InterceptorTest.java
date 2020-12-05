package com.leo.boot.aop.interceptor;

import com.leo.boot.aop.domain.MockUser;
import com.leo.boot.aop.service.MockUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InterceptorTest {

    @Autowired
    private MockUserService mockUserService;

    @Test
    public void test_intercept_user() {
        final MockUser user = new MockUser().setId("123456").setName("张三");
        MockUser interceptedUser = mockUserService.intercept(user);
        assertEquals("intercepted", interceptedUser.getName());
    }

    @Test
    public void test_intercept_without_user() {
        MockUser nullUser = mockUserService.intercept();
        assertNull(nullUser);
    }
}