package com.leo.boot.ws;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.leo.boot.ws.domain.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WsApplicationTest {

	@Autowired
	private User userClient;
	
	@Autowired
	private User user;

	@Test
	public void testUserClient() {
		assertEquals(user.say(), userClient.say());
	}
	
}
