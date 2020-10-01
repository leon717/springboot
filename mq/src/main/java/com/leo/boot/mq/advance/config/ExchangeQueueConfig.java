package com.leo.boot.mq.advance.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExchangeQueueConfig {

    public static final String MESSAGE = "topic.message";
    public static final String MESSAGES = "topic.messages";
    public static final String DELAY_QUEUE = "delay.queue";

    @Bean
    public Queue messageQueue() {
        return new Queue(MESSAGE);
    }

    @Bean
    public Queue messagesQueue() {
        return new Queue(MESSAGES);
    }

    @Bean
    public Queue delayQueue() {
        return new Queue(DELAY_QUEUE);
    }

}
