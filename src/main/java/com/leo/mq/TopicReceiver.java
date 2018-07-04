package com.leo.mq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.leo.config.RabbitMQExchangeConfig;

@Component
@RabbitListener(queues = RabbitMQExchangeConfig.message)
public class TopicReceiver {
	
    @RabbitHandler
    public void process(String hello) {
        System.out.println("Receiver  : " + hello);
    }
}
