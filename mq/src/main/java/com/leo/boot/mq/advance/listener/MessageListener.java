package com.leo.boot.mq.advance.listener;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.leo.boot.mq.advance.config.ExchangeQueueConfig;

@Component
@RabbitListener(queues = ExchangeQueueConfig.MESSAGE)
public class MessageListener {

	@RabbitHandler
	public void process(String content) {
		System.out.println("MessageReceiver Receive : " + content);
	}

}
