package com.leo.mq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.leo.domain.User;

@Component
public class UserSender {
	
	@Autowired
    private AmqpTemplate rabbitTemplate;

    public void send(User user) {
        System.out.println("Sender : " + user);
		rabbitTemplate.convertAndSend("user", user);
	}
}
