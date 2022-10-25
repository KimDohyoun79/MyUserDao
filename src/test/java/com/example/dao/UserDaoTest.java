package com.example.dao;

import com.example.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoTest {

    @Test
    @DisplayName("addAndGet")
    void addAndGet(){
        UserDao userDao = new UserDao();
        User user = new User("6", "두현","123");

        //add
        userDao.add(user);

        //get
        User getUser = userDao.findById("6");
        Assertions.assertEquals("두현", getUser.getName());

    }
}