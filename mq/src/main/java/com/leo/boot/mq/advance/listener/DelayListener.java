package com.leo.boot.mq.advance.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static com.leo.boot.mq.advance.config.ExchangeQueueConfig.DELAY_QUEUE;

@Slf4j
@Component
@RabbitListener(queues = DELAY_QUEUE)
public class DelayListener {

    @RabbitHandler
    public void process(String content) {
        log.info("DelayMessageReceiver Receive : " + content);
    }

}
