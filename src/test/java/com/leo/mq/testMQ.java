package com.leo.mq;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.leo.domain.User;
import com.leo.domain.User.Gender;

@RunWith(SpringRunner.class)
@SpringBootTest
public class testMQ {
	
    @Autowired
    private Sender sender;
    
    @Autowired
    private UserSender userSender;
    
    @Autowired
    private TopicSender topicSender;
    
    @Test
    public void send() throws Exception {
    	sender.send(0);
    }
    
    //对于多个MQ监听，默认轮流获取队列
    @Test
    public void oneToMany() throws Exception {
        for (int i=0;i<100;i++){
        	sender.send(i);
        }
    }
    
    //测试对象传输，需要实现序列化
    @Test
    public void testUserQueue(){
    	User user = new User();
    	user.setUserName("张三");
    	user.setGender(Gender.MALE);
    	userSender.send(user);
    }
    
    //测试exchange的topic模式
    @Test
    public void tesTtopicExchange(){
    	topicSender.send1();
    	topicSender.send2();
    }
}
