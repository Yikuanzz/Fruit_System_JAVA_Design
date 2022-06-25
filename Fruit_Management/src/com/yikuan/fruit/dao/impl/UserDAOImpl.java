package com.yikuan.fruit.dao.impl;

import com.yikuan.fruit.dao.UserDAO;
import com.yikuan.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOImpl implements UserDAO {
    @Override
    public User login(Connection conn, User user) {
        // 登录验证
        User resultUser = null;
        String sql = "select * from t_user where userName=? and password=?";
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1,user.getUserName());
            pstm.setString(2,user.getPassword());

            ResultSet rs = pstm.executeQuery();
            if(rs.next()){
                resultUser = new User();
                resultUser.setId(rs.getInt("id"));
                resultUser.setUserName(rs.getString("userName"));
                resultUser.setPassword(rs.getString("password"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return resultUser;
    }
}
