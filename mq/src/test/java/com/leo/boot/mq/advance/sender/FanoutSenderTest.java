package com.leo.boot.mq.advance.sender;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FanoutSenderTest {

	@Autowired
	private FanoutSender sender;

	/**
	 * 两个Queue都能接收
	 */
	@Test
	public void testMessage() {
		sender.send("msg");
	}

}
