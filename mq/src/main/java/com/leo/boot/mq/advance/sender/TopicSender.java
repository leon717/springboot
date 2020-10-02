package com.leo.boot.mq.advance.sender;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.leo.boot.mq.advance.config.ExchangeConfig;
import com.leo.boot.mq.advance.config.ExchangeQueueConfig;

@Component
public class TopicSender {

	@Autowired
	private AmqpTemplate rabbitTemplate;

	public void sendMessage(String context) {
		rabbitTemplate.convertAndSend(ExchangeConfig.TOPIC_EXCHANGE, ExchangeQueueConfig.MESSAGE, context);
	}

	public void sendMessages(String context) {
		rabbitTemplate.convertAndSend(ExchangeConfig.TOPIC_EXCHANGE, ExchangeQueueConfig.MESSAGES, context);
	}
	
}
