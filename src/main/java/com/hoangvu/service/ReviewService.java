package com.hoangvu.service;

import com.hoangvu.dao.ReviewDAO;
import com.hoangvu.dto.ResultDTO;
import com.hoangvu.entity.Review;

import java.sql.Date;
import java.util.List;

public class ReviewService {

    private ReviewDAO reviewDAO;

    public ReviewService() {
        this.reviewDAO = new ReviewDAO();
    }

    public ResultDTO<Review> save(Review review) {
        try {
            review.setCreateDate(new Date(System.currentTimeMillis()));
            int row = this.reviewDAO.create(review);
            if (row != 1) {
                throw new RuntimeException("Evaluation failed");
            }
            return new ResultDTO<>(review, false, "Successful evaluation");
        } catch(Exception ex) {
            return new ResultDTO<>(null, true, "Evaluation failed");
        }
    }

    public Review isReviewSubjectByUsername(String subjectCode, String username) {
        try {
            return this.reviewDAO.findByUsernameAndSubjectCode(username, subjectCode);
        } catch(Exception ex) {
            return null;
        }
    }

    public List<Review> findBySubjectCode(String subjectCode) {
        return this.reviewDAO.findBySubjectCode(subjectCode);
    }

    public List<Review> findAll() {
        return this.reviewDAO.findAll();
    }
}