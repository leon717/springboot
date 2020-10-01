package com.leo.boot.mq.advance.sender;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.leo.boot.mq.advance.config.ExchangeConfig.DELAY;
import static com.leo.boot.mq.advance.config.ExchangeQueueConfig.DELAY_QUEUE;

@Component
public class DelaySender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send(String context, Integer delayTime) {
        rabbitTemplate.convertAndSend(DELAY, DELAY_QUEUE, context, message -> {
            message.getMessageProperties().setDelay(delayTime * 1000);
            return message;
        });
    }

}
