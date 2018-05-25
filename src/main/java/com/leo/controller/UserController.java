package com.leo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leo.domain.User;
import com.leo.repo.UserRepository;

@RestController
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping("/getUser")
	public User index(){
		User user = new User();
		user.setUserName("小明");
		user.setPassWord("123456");
		return user;
	}
	
	@RequestMapping("/getUserPage")
	public Page<User> indexPage(){
		return userRepository.findAll(new PageRequest(1, 2, new Sort(Direction.DESC,"id")));
	}
}
