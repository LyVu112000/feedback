package com.hoangvu.mapper;

import com.hoangvu.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements IMapper<User> {

    @Override
    public User map(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt("id"));
        user.setUsername(resultSet.getString("username"));
        user.setPassword(resultSet.getString("password"));
        user.setPhone(resultSet.getString("phone"));
        user.setFullName(resultSet.getString("full_name"));
        user.setRole(resultSet.getInt("role"));
        user.setAddress(resultSet.getString("address"));
        return user;
    }
}