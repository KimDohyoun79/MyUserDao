package com.example.dao;

import com.example.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
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

    UserDao userDao;
    @BeforeEach
    void setUp() {
        this.userDao = context.getBean("awsUserDao", UserDao.class);
    }

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

    }

    @Test
    void count() throws SQLException {
        User user1 = new User("1","11","111");
        User user2 = new User("2","22","222");
        User user3 = new User("3","33","333");

        userDao.deleteAll();
        assertEquals(0, userDao.getCount());

        userDao.add(user1);
        userDao.add(user2);
        userDao.add(user3);
        assertEquals(3, userDao.getCount());
    }

    @Test
    void findByid(){
        //findByid
        User getUser = userDao.findById("2");
        Assertions.assertEquals("22", getUser.getName());

    }
}