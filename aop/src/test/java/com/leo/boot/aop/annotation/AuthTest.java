package com.leo.boot.aop.annotation;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.leo.boot.aop.annotation.Auth.RoleType;
import com.leo.boot.aop.service.MockBusinessService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthTest {

	@Autowired
	private MockBusinessService mockBusinessService;

	@Test
	public void testAdmin() {
	    boolean result = mockBusinessService.doBusiness(RoleType.ADMIN);
	    assertTrue(result);
	}

	@Test(expected = Exception.class)
	public void testOther() {
	    mockBusinessService.doBusiness(RoleType.OTHER);
	}

}
