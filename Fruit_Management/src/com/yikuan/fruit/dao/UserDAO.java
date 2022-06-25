package com.yikuan.fruit.dao;

import com.yikuan.model.User;

import java.sql.Connection;

public interface UserDAO {
    public User login(Connection conn, User user);

}
