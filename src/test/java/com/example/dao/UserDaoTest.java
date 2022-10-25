package com.example.dao;

import com.example.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = UserDaoFactory.class)
class UserDaoTest {

    @Autowired
    ApplicationContext context;

    //4. addAndGet 테스트 만들기
    @Test
    @DisplayName("addAndGet")
    void addAndGet() throws SQLException {
        UserDao userDao = context.getBean("awsUserDao", UserDao.class);
        //deleteAll
        userDao.deleteAll();

        String id = "1";
        User user = new User(id, "두현","1234");

        //add
        userDao.add(user);

        //findByid
        User getUser = userDao.findById(id);
        Assertions.assertEquals("두현", getUser.getName());

        //getCount
        assertEquals(1, userDao.getCount());

    }
}