package com.leo.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.leo.domain.Address;
import com.leo.domain.User;
import com.leo.repo.AddressRepository;
import com.leo.repo.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestAdressRepository {
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Test
	public void tsetSave(){
		User user = userRepository.findByUserName("张三");
		Address address = new Address("1","msg");
//		addressRepository.save(address);
		address.setUser(user);
		addressRepository.save(address);
	}
}
