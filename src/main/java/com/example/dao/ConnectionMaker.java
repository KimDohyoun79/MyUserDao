package com.example.dao;

import java.sql.Connection;
import java.sql.SQLException;

// 5. Interface적용해서 Connection 분리
public interface ConnectionMaker {
    Connection makeConnection() throws SQLException;
}
