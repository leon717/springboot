package com.leo.mq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.leo.config.RabbitMQExchangeConfig;

@Component
@RabbitListener(queues = RabbitMQExchangeConfig.messages)
public class TopicReceiver2 {
	
    @RabbitHandler
    public void process(String hello) {
        System.out.println("Receiver2  : " + hello);
    }
}
