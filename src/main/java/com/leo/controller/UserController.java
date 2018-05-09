package com.leo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leo.domain.User;

@RestController
public class UserController {
	
	@RequestMapping("/getUser")
	public User index(){
		User user = new User();
		user.setUserName("小明");
		user.setPassWord("123456");
		return user;
	}
}
