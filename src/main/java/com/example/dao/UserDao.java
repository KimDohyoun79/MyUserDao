package com.example.dao;

import com.example.domain.User;

import java.sql.*;
import java.util.Map;

public class UserDao {

    ConnectionMaker connectionMaker;

    public UserDao() {
        connectionMaker = new AwsConnectionMaker();
    }

    public UserDao(ConnectionMaker connectionMaker) {
        this.connectionMaker = connectionMaker;
    }

    // 1. Connection 분리
    private Connection makeConnection() throws SQLException {
        Map<String, String> env = System.getenv();

        Connection c = DriverManager.getConnection(env.get("DB_HOST"), env.get("DB_USER"), env.get("DB_PASSWORD"));

        return c;
    }

    public void add(User user) {
        Connection c = null;

        try {
            // DB접속
            c = connectionMaker.makeConnection();

            // Query문 작성
            PreparedStatement pstmt = c.prepareStatement("INSERT INTO users(id, name, password) VALUES(?,?,?);");
            pstmt.setString(1, user.getId());
            pstmt.setString(2, user.getName());
            pstmt.setString(3, user.getPassword());

            // Query문 실행
            pstmt.executeUpdate();

            pstmt.close();
            c.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public User findById(String id) {
        Connection c = null;
        try {
            // DB접속
            c = connectionMaker.makeConnection();

            // Query문 작성
            PreparedStatement pstmt = c.prepareStatement("SELECT * FROM users WHERE id = ?");
            pstmt.setString(1, id);

            // Query문 실행
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            User user = new User(rs.getString("id"), rs.getString("name"), rs.getString("password"));

            rs.close();
            pstmt.close();
            c.close();

            return user;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteAll() throws SQLException {
        Connection c = null;
        PreparedStatement ps = null;

        try {
            c = connectionMaker.makeConnection();
            ps = c.prepareStatement("DELETE FROM users");
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                }
            }
            if (c != null) {
                try {
                    c.close();
                } catch (SQLException e) {
                }
            }
        }


    }

    public int getCount() throws SQLException {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            c = connectionMaker.makeConnection();
            ps = c.prepareStatement("SELECT count(*) FROM users");
            rs = ps.executeQuery();
            rs.next();
            int count = rs.getInt(1);
            return count;
        } catch (SQLException e) {
            throw e;
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                }
            }
            if (c != null) {
                try {
                    c.close();
                } catch (SQLException e) {
                }
            }
        }
    }
}