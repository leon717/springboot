package com.leo.boot.aop.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.leo.boot.aop.annotation.Auth.RoleType;
import com.leo.boot.aop.service.BusinessService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BusinessServiceImplTest {

	@Autowired
	private BusinessService businessService;

	@Test
	public void testAdmin() {
		businessService.doBusiness(RoleType.ADMIN);
	}

	@Test(expected = RuntimeException.class)
	public void testOther() {
		businessService.doBusiness(RoleType.OTHER);
	}

}
