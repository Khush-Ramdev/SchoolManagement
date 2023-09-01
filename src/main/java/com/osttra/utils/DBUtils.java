package com.osttra.utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtils {

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/assignmentTwo", "root", "khush");
            return connection;
        } catch (Exception e) {
            System.out.println("Inside catch of getConnection()...");
            e.printStackTrace();
        }
        return connection;
    }
}
