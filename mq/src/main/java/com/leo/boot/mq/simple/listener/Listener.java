package com.leo.boot.mq.simple.listener;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.leo.boot.mq.domain.Msg;
import com.leo.boot.mq.simple.config.QueueConfig;

@Component
@RabbitListener(queues = QueueConfig.SIMPLE_QUEUE)
public class Listener {

	@RabbitHandler
	public void process(String content) {
		System.out.println("Receive String : " + content);
	}

	@RabbitHandler
	public void process(Msg<?> msg) {
		System.out.println("Receive Msg : " + msg);
	}

}
