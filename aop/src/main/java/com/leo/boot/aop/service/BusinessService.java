package com.leo.boot.aop.service;

import com.leo.boot.aop.annotation.Auth.RoleType;

public interface BusinessService {

	void doBusiness(RoleType role);
	
}
