package com.leo.boot.mq.advance.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 绑定关系是存在RabbitMQ上，一次绑定后，就会永久绑定，需要去RabbitMQ上解除绑定
 */
@Configuration
public class ExchangeConfig {

	public static final String TOPIC = "topic";
	
	public static final String FANOUT = "fanout";

	// exchange(fanout、direct、topic等)
	@Bean
	public Exchange topicExchange() {
		return new TopicExchange(TOPIC);
	}
	
	@Bean
	public Exchange fanoutExchange() {
		return new FanoutExchange(FANOUT);
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
	
}
