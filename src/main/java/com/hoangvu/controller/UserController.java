package com.hoangvu.controller;

import com.hoangvu.constant.AppConstant;
import com.hoangvu.dto.ResultDTO;
import com.hoangvu.entity.User;
import com.hoangvu.service.UserService;
import com.hoangvu.util.ConvertUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = {"/login", "/logout"})
public class UserController extends HttpServlet {

    private UserService userService;

    @Override
    public void init() throws ServletException {
        this.userService = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.userService.setHttpSession(req.getSession());
        String endpoint = req.getServletPath();

        if ("/login".equals(endpoint)) {
            req.getRequestDispatcher("login.jsp").forward(req, resp);
            return;
        }
        this.userService.logout();
        resp.sendRedirect(req.getContextPath()+"/login");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String endpoint = req.getServletPath();
        if ("/login".equals(endpoint)) {
            User user = ConvertUtil.requestParamToObject(req, User.class);
            ResultDTO<User> resultLogin = this.userService.login(user);
            if (resultLogin.isError()) {
                req.setAttribute("error", resultLogin.getMessage());
                req.getRequestDispatcher("login.jsp").forward(req, resp);
                return;
            }
            User data = resultLogin.getData();
            if (data.getRole() == AppConstant.ROLE_ADMIN) {
                resp.sendRedirect(req.getContextPath()+"/admin/responses");
            } else {
                resp.sendRedirect(req.getContextPath()+"/assessment");
            }
            return;
        }
    }

    @Override
    public void destroy() {
        this.userService = null;
    }
}