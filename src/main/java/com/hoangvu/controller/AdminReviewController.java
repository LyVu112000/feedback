package com.hoangvu.controller;

import com.hoangvu.dto.ReviewDTO;
import com.hoangvu.entity.Review;
import com.hoangvu.entity.Subject;
import com.hoangvu.entity.User;
import com.hoangvu.service.ReviewService;
import com.hoangvu.service.SubjectService;
import com.hoangvu.service.UserService;
import com.hoangvu.util.ConvertUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@WebServlet("/admin/assessment")
public class AdminReviewController extends HttpServlet {

    private ReviewService reviewService;
    private SubjectService subjectService;
    private UserService userService;

    @Override
    public void init() throws ServletException {
        this.reviewService = new ReviewService();
        this.subjectService = new SubjectService();
        this.userService = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Subject subject = ConvertUtil.requestParamToObject(req, Subject.class);

        List<Review> reviewList = this.reviewService.findAll();

        if (Objects.nonNull(subject.getCode())) {
            reviewList = this.reviewService.findBySubjectCode(subject.getCode());
        }

        List<ReviewDTO> reviews = reviewList
                .stream()
                .map(review -> {
                    ReviewDTO reviewDTO = new ReviewDTO(review);

                    User user = this.userService.findByUsername(review.getUsername());
                    Subject s = this.subjectService.findByCode(review.getSubjectCode());
                    reviewDTO.setStudentName(user.getFullName());
                    reviewDTO.setSubjectName(s.getName());

                    return reviewDTO;
                }).collect(Collectors.toList());
        req.setAttribute("reviews", reviews);
        req.setAttribute("subjects", this.subjectService.findAll());
        req.getRequestDispatcher("/admin-ui/review/index.jsp").forward(req, resp);

    }

    @Override
    public void destroy() {
        this.reviewService = null;
        this.subjectService = null;
        this.userService = null;
    }
}