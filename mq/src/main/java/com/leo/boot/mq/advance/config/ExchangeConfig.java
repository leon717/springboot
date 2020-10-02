package com.leo.boot.mq.advance.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

import static com.leo.boot.mq.advance.config.ExchangeQueueConfig.DELAY_QUEUE;

/**
 * 绑定关系是存在RabbitMQ上，一次绑定后，就会永久绑定，需要去RabbitMQ上解除绑定
 */
@Configuration
public class ExchangeConfig {

    public static final String TOPIC_EXCHANGE = "topic.exchange";

    public static final String FANOUT_EXCHANGE = "fanout.exchange";

    public static final String DELAY_EXCHANGE = "delay.exchange";

    // exchange(fanout、direct、topic等)
    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(TOPIC_EXCHANGE);
    }

    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(FANOUT_EXCHANGE);
    }

    @Bean
    public CustomExchange delayExchange() {
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("x-delayed-type", "topic");
        return new CustomExchange(DELAY_EXCHANGE, "x-delayed-message", true, false, args);
    }

    // messageQueue绑定只接受topic.message发送消息
    @Bean
    public Binding bindingTopicMessage(Queue messageQueue, TopicExchange topicExchange) {
        return BindingBuilder.bind(messageQueue).to(topicExchange).with("topic.message");
    }

    // messagesQueue绑定接受topic下所有消息
    // *代表表一个单词，#表所有单词
    @Bean
    public Binding bindingTopicMessages(Queue messagesQueue, TopicExchange topicExchange) {
        return BindingBuilder.bind(messagesQueue).to(topicExchange).with("topic.#");
    }

    // messageQueue绑定接受fanout所有信息
    @Bean
    public Binding bindingFanoutMessage(Queue messageQueue, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(messageQueue).to(fanoutExchange);
    }

    // messagesQueue绑定接受fanout所有信息
    @Bean
    public Binding bindingFanoutMessages(Queue messagesQueue, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(messagesQueue).to(fanoutExchange);
    }

    @Bean
    public Binding bindingDelayQueue(Queue delayQueue, CustomExchange delayExchange) {
        return BindingBuilder.bind(delayQueue).to(delayExchange).with(DELAY_QUEUE).noargs();
    }

}
