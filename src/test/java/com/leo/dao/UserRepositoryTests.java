package com.leo.dao;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.context.junit4.SpringRunner;

import com.leo.domain.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTests {

	@Resource
	private UserRepository userRepository;

	@Test
	public void test() throws Exception {
		
		//insert
		userRepository.save(new User("张三", "123456", "sanzhang@leo.com","zs",new Date()));
		userRepository.save(new User("李四", "123456", "sili@leo.com","ls",new Date()));
		userRepository.save(new User("王五", "123456", "wuwang@leo.com","ww",new Date()));
		userRepository.save(new User("王六", "123456", "wuwang6@leo.com","ww6",new Date()));
		userRepository.save(new User("王七", "123456", "wuwang7@leo.com","ww7",new Date()));
		userRepository.save(new User("王八", "123456", "wuwang8@leo.com","ww8",new Date()));
		
		//update
		User user = userRepository.findByUserName("王五");
		user.setNickName("new");
		userRepository.save(user);
		
		userRepository.modifyByIdAndUserId("王九", 2);
		
		//findAll
		List<User> userList = userRepository.findAll();
		System.out.println(userList);
		//分页
		int page = 0;
		int size = 3;
		Pageable pageable = new PageRequest(page, size, new Sort(Direction.DESC,"id"));
		Page<User> users = userRepository.findAll(pageable);
		Page<User> users2 = userRepository.findByOrderByIdDesc(pageable);//和上面等效
		System.out.println(users.getContent());
		System.out.println(users2.getContent());
		
		//hql
		User user2 = userRepository.findByEmailAddress("wuwang6@leo.com");
		System.out.println(user2);
		
		//sql
		List<User> list = userRepository.FindByNickNameBySql("new");
		System.out.println(list);

		userRepository.delete(userRepository.findByUserName("张三"));
		userRepository.delete(userRepository.findByUserNameOrEmail(null, "sili@leo.com"));
	}

}