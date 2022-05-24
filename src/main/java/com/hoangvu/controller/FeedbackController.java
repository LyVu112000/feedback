package com.hoangvu.controller;

import com.hoangvu.constant.AppConstant;
import com.hoangvu.dto.ResultDTO;
import com.hoangvu.entity.Feedback;
import com.hoangvu.entity.User;
import com.hoangvu.service.FeedbackService;
import com.hoangvu.util.ConvertUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/send-feedback")
public class FeedbackController extends HttpServlet {

    private FeedbackService feedbackService;

    @Override
    public void init() throws ServletException {
        this.feedbackService = new FeedbackService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute(AppConstant.SESSION_LOGIN_USER);

        req.setAttribute("user", user);
        req.getRequestDispatcher("/student-ui/feedback/form.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Feedback feedback = ConvertUtil.requestParamToObject(req, Feedback.class);

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute(AppConstant.SESSION_LOGIN_USER);

        feedback.setUsername(user.getUsername());

        ResultDTO<Feedback> resultFeedback = this.feedbackService.save(feedback);
        if (resultFeedback.isError()) {
            req.setAttribute("error", resultFeedback.getMessage());
            req.getRequestDispatcher("/student-ui/feedback/form.jsp").forward(req, resp);
            return;
        }
        resp.sendRedirect(req.getContextPath()+"/send-feedback");
    }

    @Override
    public void destroy() {
        this.feedbackService = null;
    }
}