package com.yikuan.fruit.dao.impl;

import com.yikuan.fruit.dao.FruitDAO;
import com.yikuan.fruit.pojo.Fruit;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FruitDAOImpl implements FruitDAO {
    final  String Driver = "com.mysql.jdbc.Driver";
    final String url = "jdbc:mysql://localhost:3306/fruitdb?useSSL=false&useUnicode=true&characterEncoding=utf-8";
    final String user = "root";
    final String pwd = "123456";

    Connection conn;
    ResultSet rs;
    PreparedStatement psmt;

    private Connection getConn(){

        try {
            // 1.加载驱动
            Class.forName(Driver);

            // 2.通过驱动管理器获取链接对象
            conn = DriverManager.getConnection(url, user, pwd);

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        return conn;
    }

    private void close(ResultSet rs, PreparedStatement psmt, Connection conn){
        try {
            if (rs!=null){
                rs.close();
            }
            if (psmt!=null){
                psmt.close();
            }
            if (conn!=null&&!conn.isClosed()){
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Fruit> getFruitList() {
        List<Fruit> fruitList = new ArrayList<>();
        try {
            // 加载驱动并且获取连接
            conn = getConn();
            // 编写SQL语句
            String sql = "select * from t_fruit";
            // 创建预处理命令对象
            psmt = conn.prepareStatement(sql);
            // 执行查询
            rs = psmt.executeQuery();
            // 解析结果集
            while(rs.next()){
                int fid = rs.getInt(1);
                String fname = rs.getString(2);
                int price = rs.getInt(3);
                int fcount = rs.getInt(4);
                String remark = rs.getString(5);

                Fruit fruit = new Fruit(fid, fname, price, fcount, remark);

                fruitList.add(fruit);
            }
            return fruitList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(rs, psmt, conn);
        }
    }

    @Override
    public boolean addFruit(Fruit fruit) {
        // 1.加载驱动
        try {
            // 加载驱动,通过驱动管理器获取链接对象
            conn =getConn();

            // 编写SQL语句
            String sql = "insert into t_fruit values(0, ?, ?, ?, ?)";

            // 编写内容
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, fruit.getFname());
            psmt.setInt(2, fruit.getPrice());
            psmt.setInt(3, fruit.getFcount());
            psmt.setString(4, fruit.getRemark());

            return psmt.executeUpdate() > 0;

        } catch ( SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            close(rs, psmt, conn);
        }
    }

    @Override
    public boolean updateFruit(Fruit fruit) {

        try {
            // 加载驱动,通过驱动管理器获取链接对象
            conn = getConn();

            // 编写SQL语句
            String sql = "update t_fruit set fcount = ? where fid = ?";

            // 编写相关参数
            psmt = conn.prepareStatement(sql);
            psmt.setInt(1, fruit.getFcount());
            psmt.setInt(2, fruit.getFid());
            return psmt.executeUpdate() > 0;
        } catch ( SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(rs, psmt, conn);
        }

    }

    @Override
    public Fruit getFruitByFname(String fname) {

        try {
            // 加载驱动,连接数据库
            conn = getConn();

            // 编写SQL语句
            String sql = "select * from t_fruit where fname like ?";

            // 添加参数
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, fname);

            // 获得结果集，并进行解析
            rs = psmt.executeQuery();
            if(rs.next()){
                int fid = rs.getInt(1);
                int price = rs.getInt(3);
                int fcount = rs.getInt(4);
                String remark = rs.getString(5);

                return new Fruit(fid, fname, price, fcount, remark);
            }
        } catch ( SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(rs, psmt, conn);
        }
        return null;
    }

    @Override
    public boolean delFruit(String fname) {
        try {
            // 加载驱动,连接数据库
            conn = getConn();

            // 编写SQL语句
            String sql = "delete from t_fruit where fname like ?";

            // 添加参数
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, fname);
            psmt.executeUpdate();
            return psmt.executeUpdate() > 0;
        } catch ( SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(rs, psmt, conn);
        }
    }
}
