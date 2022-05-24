package com.hoangvu.dao;

import com.hoangvu.entity.Feedback;
import com.hoangvu.jdbc.JdbcTemplate;
import com.hoangvu.mapper.FeedbackMapper;
import com.hoangvu.mapper.IMapper;

import java.util.List;

public class FeedbackDAO {

    private JdbcTemplate<Feedback> jdbcTemplate;

    public FeedbackDAO() {
        IMapper<Feedback> feedbackMapper = new FeedbackMapper();
        this.jdbcTemplate = new JdbcTemplate<>(feedbackMapper, Feedback.class);
    }

    public int save(Feedback feedback) {
        return this.jdbcTemplate
                .save("INSERT INTO feedback(username, create_date, content) VALUES(?, ?, ?)", feedback.getUsername(), feedback.getCreateDate(), feedback.getContent())
                .executeSave();
    }

    public List<Feedback> findAll() {
        return this.jdbcTemplate
                .select("SELECT * FROM feedback ORDER BY create_date DESC")
                .executeQueryList();
    }

    public List<Feedback> findByUsername(String username) {
        return this.jdbcTemplate
                .select("SELECT * FROM feedbabck WHERE username = ?", username)
                .executeQueryList();
    }
}