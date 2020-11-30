package com.hongyuchang.generatemillionrecord.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBCHelper {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/java0?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=GMT%2B8";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static Connection connection;

    private JDBCHelper() {}

    public static Statement getStatement() throws Exception {
        Connection connection = getConnection();
        JDBCHelper.connection = connection;
        return connection.createStatement();
    }

    public static void release(ResultSet resultSet, Statement statement) throws Exception {
        if (null != resultSet) {
            resultSet.close();
        }
        if (statement != null) {
            statement.close();
        }
        if (null != connection) {
            connection.close();
        }
    }

    public static Connection getConnection() throws Exception {
        Class.forName(DRIVER);
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
