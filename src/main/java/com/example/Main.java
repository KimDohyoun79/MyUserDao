package com.example;

import com.example.dao.UserDao;
import com.example.domain.User;

public class Main {
    public static void main(String[] args) {
        UserDao userDao = new UserDao();
        userDao.add(new User("7","32","123"));
        User user = userDao.findById("5");
        System.out.println(user.getName());
    }
}