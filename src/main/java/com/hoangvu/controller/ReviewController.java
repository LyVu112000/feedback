package com.hoangvu.controller;

import com.hoangvu.constant.AppConstant;
import com.hoangvu.dto.SubjectDTO;
import com.hoangvu.entity.Review;
import com.hoangvu.entity.Subject;
import com.hoangvu.entity.User;
import com.hoangvu.service.ReviewService;
import com.hoangvu.service.SubjectService;
import com.hoangvu.util.ConvertUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@WebServlet("/assessment")
public class ReviewController extends HttpServlet {

    private ReviewService reviewService;
    private SubjectService subjectService;

    @Override
    public void init() throws ServletException {
        this.reviewService = new ReviewService();
        this.subjectService = new SubjectService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Subject subject = ConvertUtil.requestParamToObject(req, Subject.class);
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute(AppConstant.SESSION_LOGIN_USER);

        List<SubjectDTO> subjects = this.subjectService
                .findByNameLike(subject.getName())
                .stream().map(s -> {
                    SubjectDTO subjectDTO = new SubjectDTO(s);
                    Review review = this.reviewService.isReviewSubjectByUsername(s.getCode(), user.getUsername());
                    subjectDTO.setReview(Objects.nonNull(review));
                    if (Objects.nonNull(review)) {
                        subjectDTO.setContentReview(review.getContent());
                    }
                    return subjectDTO;
                }).collect(Collectors.toList());

        req.setAttribute("subjects", subjects);
        req.getRequestDispatcher("/student-ui/review/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute(AppConstant.SESSION_LOGIN_USER);

        req.getParameterMap().forEach((sbCode, content) -> {
            if (content.length > 0 && !content[0].trim().isEmpty()) {
                Review review = new Review();
                review.setUsername(user.getUsername());
                review.setSubjectCode(sbCode);
                review.setContent(content[0]);
                this.reviewService.save(review);
            }
        });
        resp.sendRedirect(req.getContextPath()+"/assessment");
    }

    @Override
    public void destroy() {
        this.reviewService = null;
        this.subjectService = null;
    }
}