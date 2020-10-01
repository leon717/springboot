package com.leo.boot.mq.advance.sender;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DelaySenderTest extends TestCase {

    @Autowired
    private DelaySender sender;

    @Test
    public void testMessage() {
        sender.send("delay 1s", 1);
        sender.send("delay 2s", 2);
        sender.send("delay 5s", 5);
        sender.send("delay 4s", 4);
        sender.send("delay 3s", 3);
    }

}