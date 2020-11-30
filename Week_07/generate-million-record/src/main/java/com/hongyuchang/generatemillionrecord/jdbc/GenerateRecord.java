package com.hongyuchang.generatemillionrecord.jdbc;

import java.io.Console;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class GenerateRecord {
//    public int count=0;
    //耗时 1337380 毫秒
    public void generateMillionRecordWithJDBC(){
        Connection conn = null;
        Statement stmt = null;
        int loopTimes = 1000000;
        try {
            long startTime = Calendar.getInstance().getTimeInMillis();
            conn = JDBCHelper.getConnection();
            stmt = conn.createStatement();
            int i = loopTimes - 1000000;
            for(i=0;i< loopTimes;i++){
                String sql ="insert into tb_order(id,order_code,order_time,address,total_amount,user_id)" +
                        "values("+i+",'"+ UUID.randomUUID().toString() +"',now(),'~',"+i+","+(Math.round(Math.random()*10)%5+1)+")";
                stmt.execute(sql);
            }
            long endTime = Calendar.getInstance().getTimeInMillis();
            System.out.println("耗时："+(endTime - startTime));
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if(stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if(conn!= null) {
                try {
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }

    }
    //耗时：1331045
    public void generateMillionRecordWithJDBCBatch(){
        String sql ="insert into tb_order(id,order_code,order_time,address,total_amount,user_id)values(?,?,now(),'~',?,?)";
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        int loopTimes = 1000000;
        try {
            long startTime = Calendar.getInstance().getTimeInMillis();
            conn = JDBCHelper.getConnection();
            preparedStatement = conn.prepareStatement(sql);
            int i = loopTimes - 1000000;
            for(i=0;i< loopTimes;i++){
                preparedStatement.setLong(1,i);;
                preparedStatement.setString(2,UUID.randomUUID().toString());
                preparedStatement.setInt(3,i);;
                preparedStatement.setLong(4,(Math.round(Math.random()*10)%5+1));;
                preparedStatement.execute();
            }
            long endTime = Calendar.getInstance().getTimeInMillis();
            System.out.println("耗时："+(endTime - startTime));
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if(preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException throwable) {
                    throwable.printStackTrace();
                }
            }
            if(conn!= null) {
                try {
                    conn.close();
                } catch (SQLException throwable) {
                    throwable.printStackTrace();
                }
            }
        }
    }
}
