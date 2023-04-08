package com.example.casestudy2.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class ConnectDAO {
    private static String jdbcURL = "jdbc:mysql://localhost:3306/films_database?useSSL=false";
    private static String jbbcUserName = "root";
    private static String jdbcPassword = "123456";

    public ConnectDAO() {
    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jbbcUserName, jdbcPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    public PreparedStatement handleSQL(String sql) {
        PreparedStatement preparedStatement = null;
        try {
            Connection connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            return preparedStatement;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return preparedStatement;
    }
}
