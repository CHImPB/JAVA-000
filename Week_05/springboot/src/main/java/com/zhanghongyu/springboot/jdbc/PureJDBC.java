package com.zhanghongyu.springboot.jdbc;

import lombok.var;

import java.sql.*;
import java.util.UUID;

public class PureJDBC {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/test?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=GMT%2B8";

    static final String USER = "root";
    static final String PASS = "123456";
    static final int COUNT = 100;
    public void crud()
    {
        Connection conn = null;
        Statement stmt = null;
        // 注册 JDBC 驱动
        try {
            Class.forName(JDBC_DRIVER);
            // 打开链接
            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            String sqlInsert = null;
            //批量执行插入
            stmt = conn.createStatement();
            for(int i = 0; i< COUNT; i++){
                String studentName= UUID.randomUUID().toString().replaceAll("-", "");
                sqlInsert = "insert into student(age,name ) values("+i+",'"+studentName+"')";
                stmt.execute(sqlInsert,new String[]{String.valueOf(i),studentName});
            }
            stmt.close();
            //查询数据
            String sqlQuery = "SELECT ID,NAME,AGE FROM STUDENT";
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sqlQuery);
            while (rs.next()){
                int id  = rs.getInt("id");
                String name = rs.getString("name");
                String age = rs.getString("age");
                // 输出数据
                System.out.print("ID: " + id+", 姓名: " + name+", age: " + age+"\n");
            }

            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            // 关闭资源
            try{
                if(stmt!=null){
                    stmt.close();
                }
            }catch(SQLException se2){
            }// 什么都不做
            try{
                if(conn!=null){
                    conn.close();
                }
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
    }
}
