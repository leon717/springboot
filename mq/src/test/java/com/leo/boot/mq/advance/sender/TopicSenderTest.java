package com.leo.boot.mq.advance.sender;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TopicSenderTest {

	@Autowired
	private TopicSender sender;

	/**
	 * 两个Queue都能接收
	 */
	@Test
	public void testMessage() {
		sender.sendMessage("msg");
	}
	
	/**
	 * 只有MessagesQueue接受
	 */
	@Test
	public void testMessages() {
		sender.sendMessages("msg");
	}
	
}
