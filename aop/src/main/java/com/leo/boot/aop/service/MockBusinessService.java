package com.leo.boot.aop.service;

import org.springframework.stereotype.Service;

import com.leo.boot.aop.annotation.Auth;
import com.leo.boot.aop.annotation.Auth.RoleType;

@Service
public class MockBusinessService {

	@Auth({RoleType.ADMIN, RoleType.USER})
	public boolean doBusiness(RoleType role) {
	    return true;
	}

}
