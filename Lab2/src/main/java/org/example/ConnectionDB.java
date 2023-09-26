package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
    private static final String url = "jdbc:mysql://localhost:3306/ProductManagement?useSSL=false";
    private static final String userName = "ltp";
    private static final String passWord = "123456";

    public static Connection getConnection() {
        Connection c = null;
        try {
            c = DriverManager.getConnection(url, userName, passWord);
        } catch (SQLException e) {
            throw new RuntimeException(e); // Print the exception for debugging purpos
        }
        return c;
    }
}