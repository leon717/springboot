package com.leo.config;


import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

	@Bean	//定义队列
	public Queue Queue() {
	    return new Queue("hello");
	}
}
