package com.hoangvu.dao;

import com.hoangvu.entity.User;
import com.hoangvu.jdbc.JdbcTemplate;
import com.hoangvu.mapper.IMapper;
import com.hoangvu.mapper.UserMapper;

public class UserDAO {

    private JdbcTemplate<User> jdbcTemplate;

    public UserDAO() {
        IMapper<User> userMapper = new UserMapper();
        this.jdbcTemplate = new JdbcTemplate<>(userMapper, User.class);
    }

    public User findByUsername(String username) {
        return this.jdbcTemplate
                .findOneBy("username", username);
    }
}