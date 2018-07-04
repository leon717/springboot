package com.leo.mq;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class testMQ {
	
    @Autowired
    private Sender sender;

    @Test
    public void send() throws Exception {
    	sender.send();
    }
    
}
