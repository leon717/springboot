package com.leo.boot.aop.service.impl;

import org.springframework.stereotype.Service;

import com.leo.boot.aop.annotation.Auth;
import com.leo.boot.aop.annotation.Auth.RoleType;
import com.leo.boot.aop.service.BusinessService;

@Service
public class BusinessServiceImpl implements BusinessService {

	@Auth({RoleType.ADMIN, RoleType.USER})
	@Override
	public void doBusiness(RoleType role) {
		System.out.println("do business");
	}

}
