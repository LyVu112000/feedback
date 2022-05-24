package com.hoangvu.mapper;

import com.hoangvu.entity.Review;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReviewMapper implements IMapper<Review> {

    @Override
    public Review map(ResultSet resultSet) throws SQLException {
        Review review = new Review();
        review.setId(resultSet.getInt("id"));
        review.setCreateDate(resultSet.getDate("create_date"));
        review.setUsername(resultSet.getString("username"));
        review.setSubjectCode(resultSet.getString("subject_code"));
        review.setContent(resultSet.getString("content"));
        return review;
    }
}