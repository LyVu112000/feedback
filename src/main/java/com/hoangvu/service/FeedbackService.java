package com.hoangvu.service;

import com.hoangvu.dao.FeedbackDAO;
import com.hoangvu.dto.ResultDTO;
import com.hoangvu.entity.Feedback;

import java.sql.Date;
import java.util.List;

public class FeedbackService {

    private FeedbackDAO feedbackDAO;

    public FeedbackService() {
        this.feedbackDAO = new FeedbackDAO();
    }

    public ResultDTO<Feedback> save(Feedback feedback) {
        try {
            feedback.setCreateDate(new Date(System.currentTimeMillis()));
            int row = this.feedbackDAO.save(feedback);
            if (row != 1) {
                throw new RuntimeException("");
            }
            return new ResultDTO<>(feedback, false, "");
        } catch(Exception ex) {
            return new ResultDTO<>(null, true, "Failed responses");
        }
    }

    public List<Feedback> findAll() {
        return this.feedbackDAO.findAll();
    }

    public List<Feedback> findByUsername(String username) {
        return this.feedbackDAO.findByUsername(username);
    }
}