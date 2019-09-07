package com.leo.boot.mq.simple.sender;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.leo.boot.mq.domain.Msg;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SenderTest {

	@Autowired
	private Sender sender;

	@Test
	public void testString() {
		sender.send("msg");
	}

	@Test
	public void testMsg() {
		Msg<Integer> msg = new Msg<Integer>().setCode("200").setMsg("OK").setData(Arrays.asList(1, 2, 3));
		sender.send(msg);
	}

}
