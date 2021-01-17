package com.leo.boot.jpa.repo;

import com.leo.boot.jpa.domain.SchemaUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SchemaUserRepositoryTest {

    @Autowired
    private SchemaUserRepository schemaUserRepository;

    @Test
    public void save() {
        SchemaUser schemaUser = new SchemaUser().setName("张三");
        schemaUserRepository.save(schemaUser);
    }
}