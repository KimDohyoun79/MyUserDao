package com.example.dao;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;
import java.util.Map;

@Configuration
public class UserDaoFactory {

    @Bean
    public UserDao awsUserDao(){
        return new UserDao(new AwsConnectionMaker());
    }

    @Bean
    public UserDao localUserDao() {
        return new UserDao(new LocalConnectionMaker());
    }
}
