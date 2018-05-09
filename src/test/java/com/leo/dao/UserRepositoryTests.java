package com.leo.dao;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.leo.domain.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTests {

	@Resource
	private UserRepository userRepository;

	@Test
	public void test() throws Exception {
		
		userRepository.save(new User("张三", "123456", "sanzhang@leo.com","zs",new Date()));
		userRepository.save(new User("李四", "123456", "sili@leo.com","ls",new Date()));
		userRepository.save(new User("王五", "123456", "wuwang@leo.com","ww",new Date()));
		
		User user = userRepository.findByUserName("王五");
		user.setNickName("new");
		userRepository.save(user);

		userRepository.delete(userRepository.findByUserName("张三"));
		userRepository.delete(userRepository.findByUserNameOrEmail(null, "sili@leo.com"));
	}

}