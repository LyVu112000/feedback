package com.hoangvu.mapper;

import com.hoangvu.entity.Feedback;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FeedbackMapper implements IMapper<Feedback> {

    @Override
    public Feedback map(ResultSet resultSet) throws SQLException {
        Feedback feedback = new Feedback();
        feedback.setId(resultSet.getInt("id"));
        feedback.setUsername(resultSet.getString("username"));
        feedback.setCreateDate(resultSet.getDate("create_date"));
        feedback.setContent(resultSet.getString("content"));
        return feedback;
    }
}