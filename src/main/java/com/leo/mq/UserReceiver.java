package com.leo.mq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.leo.domain.User;

@Component
@RabbitListener(queues = "user")
public class UserReceiver {
	
    @RabbitHandler
    public void process(User user) {
        System.out.println("Receiver  : " + user);
    }
}
