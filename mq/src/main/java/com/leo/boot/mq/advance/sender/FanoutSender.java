package com.leo.boot.mq.advance.sender;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.leo.boot.mq.advance.config.ExchangeConfig;

@Component
public class FanoutSender {

	@Autowired
	private AmqpTemplate rabbitTemplate;

	public void send(String context) {
		rabbitTemplate.convertAndSend(ExchangeConfig.FANOUT_EXCHANGE, "", context);
	}
	
}
