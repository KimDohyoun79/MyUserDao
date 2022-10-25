package com.example.dao;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;
import java.util.Map;

public class UserDaoFactory {

    public UserDao awsUserDao(){
        return new UserDao(new AwsConnectionMaker());
    }

    UserDao localUserDao() {
        return new UserDao(new LocalConnectionMaker());
    }
}
