package com.yikuan.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {
    final  String Driver = "com.mysql.jdbc.Driver";
    final String url = "jdbc:mysql://localhost:3306/fruitdb?useSSL=false&useUnicode=true&characterEncoding=utf-8";
    final String user = "root";
    final String pwd = "123456";

    public Connection getCon()  {
        try {
            Class.forName(Driver);
            Connection conn = DriverManager.getConnection(url, user, pwd);
            System.out.println("数据库连接成功！");
            return conn;
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("数据库连接失败！");
            throw new RuntimeException(e);
        }

    }

    public void closeCon(Connection con){
            try {
                if (con!=null){
                con.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
    }


    public static void main(String[] args) {
        DbUtil dbUtil = new DbUtil();
        dbUtil.getCon();


    }
}
