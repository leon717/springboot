package com.leo.boot.jpa.repo;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.context.junit4.SpringRunner;

import com.leo.boot.jpa.domain.User;
import com.leo.boot.jpa.enumeration.Gender;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PageableUserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Before
    public void save() {
        User user1 = new User().setName("张三").setNick("小张").setGender(Gender.MALE);
        User user2 = new User().setName("李四").setNick("小李").setGender(Gender.FEMALE);
        User user3 = new User().setName("王五").setNick("小王").setGender(Gender.MALE);
        User user4 = new User().setName("赵六").setNick("小赵").setGender(Gender.FEMALE);
        User user5 = new User().setName("田七").setNick("小田").setGender(Gender.MALE);
        userRepository.save(Arrays.asList(user1, user2, user3, user4, user5));
    }

    @After
    public void delete() {
        userRepository.deleteAll();
    }

    @Test
    public void testPage() {
        Pageable pageable = new PageRequest(0, 20, Direction.DESC, "id", "name");
        Page<User> users = userRepository.findAll(Example.of(new User().setGender(Gender.MALE)), pageable);
        assertEquals(3, users.getNumberOfElements());
        assertEquals(3, users.getTotalElements());
    }

}
