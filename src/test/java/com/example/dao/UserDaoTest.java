package com.example.dao;

import com.example.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoTest {

    //4. addAndGet 테스트 만들기
    @Test
    @DisplayName("addAndGet")
    void addAndGet(){
        UserDao userDao = new UserDaoFactory().awsUserDao();
        String id = "2";
        User user = new User(id, "두현","1234");

        //add
        userDao.add(user);

        //get
        User getUser = userDao.findById(id);
        Assertions.assertEquals("두현", getUser.getName());

    }
}