package com.hoangvu.dao;

import com.hoangvu.entity.Review;
import com.hoangvu.jdbc.JdbcTemplate;
import com.hoangvu.mapper.IMapper;
import com.hoangvu.mapper.ReviewMapper;

import java.util.List;

public class ReviewDAO {

    private JdbcTemplate<Review> jdbcTemplate;

    public ReviewDAO() {
        IMapper<Review> reviewMapper = new ReviewMapper();
        this.jdbcTemplate = new JdbcTemplate<>(reviewMapper, Review.class);
    }

    public int create(Review review) {
        return this.jdbcTemplate
                .save("INSERT INTO review(username, subject_code, create_date, content) VALUES(?, ?,?,?)", review.getUsername(), review.getSubjectCode(), review.getCreateDate(), review.getContent())
                .executeSave();
    }

    public Review findByUsernameAndSubjectCode(String username, String subjectCode) {
        return this.jdbcTemplate
                .select("SELECT * FROM review WHERE subject_code = ? AND username = ?", subjectCode, username)
                .executeQuery();
    }

    public List<Review> findAll() {
        return this.jdbcTemplate
                .select("SELECT * FROM review ORDER BY username, create_date")
                .executeQueryList();
    }

    public List<Review> findBySubjectCode(String subjectCode) {
        return this.jdbcTemplate
                .select("SELECT * FROM review WHERE subject_code = ?", subjectCode)
                .executeQueryList();
    }
}