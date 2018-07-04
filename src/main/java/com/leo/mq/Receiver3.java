package com.leo.mq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "hello")
public class Receiver3 {
	
    @RabbitHandler
    public void process(String hello) {
        System.out.println("Receiver3  : " + hello);
    }
}
