package com.hoangvu.controller;

import com.hoangvu.entity.Feedback;
import com.hoangvu.service.FeedbackService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/responses")
public class AdminFeedbackController extends HttpServlet {

    private FeedbackService feedbackService;

    @Override
    public void init() throws ServletException {
        this.feedbackService = new FeedbackService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Feedback> feedbacks = this.feedbackService.findAll();
        req.setAttribute("feedbacks", feedbacks);
        req.getRequestDispatcher("/admin-ui/feedback/index.jsp").forward(req, resp);
    }

    @Override
    public void destroy() {
        this.feedbackService = null;
    }
}