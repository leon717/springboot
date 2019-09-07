package com.leo.boot.jpa.repo;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.leo.boot.jpa.domain.User;
import com.leo.boot.jpa.enumeration.Gender;
import com.leo.boot.jpa.spec.SpecBuilder;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpecUserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Before
    public void save() {
        User user1 = new User().setName("张三").setNick("小张").setGender(Gender.MALE);
        User user2 = new User().setName("李四").setNick("小李").setGender(Gender.FEMALE);
        User user3 = new User().setName("王五").setNick("小王").setGender(Gender.MALE);
        User user4 = new User().setName("赵六").setNick("小赵").setGender(Gender.FEMALE);
        User user5 = new User().setName("田七").setNick("小田").setGender(Gender.MALE);
        userRepository.saveAll(Arrays.asList(user1, user2, user3, user4, user5));
    }

    @After
    public void delete() {
        userRepository.deleteAll();
    }

    @Test
    public void testSpec1() {
        SpecBuilder<User> specBuilder = new SpecBuilder<>("gender=MALE");
        List<User> users = userRepository.findAll(specBuilder.build());
        assertEquals(3, users.size());
    }
    
    @Test
    public void testSpec2() {
        SpecBuilder<User> specBuilder = new SpecBuilder<>("gender$MALE&FEMALE");
        List<User> users = userRepository.findAll(specBuilder.build());
        assertEquals(5, users.size());
    }
    
    @Test
    public void testSpec3() {
        SpecBuilder<User> specBuilder = new SpecBuilder<>("creationDate>20190823");
        List<User> users = userRepository.findAll(specBuilder.build());
        assertEquals(5, users.size());
    }
    
}
