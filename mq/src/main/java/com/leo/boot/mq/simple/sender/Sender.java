package com.leo.boot.mq.simple.sender;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.leo.boot.mq.domain.Msg;
import com.leo.boot.mq.simple.config.QueueConfig;

@Component
public class Sender {

	@Autowired
	private AmqpTemplate rabbitTemplate;

	public void send(String content) {
		rabbitTemplate.convertAndSend(QueueConfig.SIMPLE_QUEUE, content);
	}

	public void send(Msg<?> msg) {
		rabbitTemplate.convertAndSend(QueueConfig.SIMPLE_QUEUE, msg);
	}

}
