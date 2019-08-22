package com.leo.boot.jpa.repo;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.leo.boot.jpa.domain.User;
import com.leo.boot.jpa.enumeration.Gender;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BaseUserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Before
    public void testSave() {
        User user = new User().setName("张三").setNick("小张").setGender(Gender.MALE).setTemp("temp");
        userRepository.save(user);
    }

    @After
    public void testDeleteByName() {
        userRepository.deleteByName("张三");
    }

    @Test
    public void testFindByName() {
        User user = userRepository.findByName("张三");
        assertEquals("张三", user.getName());
    }

    @Test
    public void testFindByNameOrNick() {
        User user = userRepository.findByNameOrNick("张三", "");
        assertEquals("张三", user.getName());
    }

    @Test
    public void testFindByNameAndNick() {
        User user = userRepository.findByNameOrNick("张三", "小张");
        assertEquals("张三", user.getName());
    }

    @Test
    public void testFindByNameByHQL() {
        User user = userRepository.findByNameByHQL("张三");
        assertEquals("张三", user.getName());
    }

    @Test
    public void testModifyNickByNameByHQL() {
        userRepository.modifyNickByNameByHQL("三", "张三");
        User user = userRepository.findByNameOrNick("", "三");
        assertEquals("张三", user.getName());
    }

    @Test
    public void testFindByNameByNative() {
        User user = userRepository.findByNameByNative("张三");
        assertEquals("张三", user.getName());
    }

    @Test
    public void testModifyNickByNameByNative() {
        userRepository.modifyNickByNameByNative("三", "张三");
        User user = userRepository.findByNameOrNick("", "三");
        assertEquals("张三", user.getName());
    }

}
