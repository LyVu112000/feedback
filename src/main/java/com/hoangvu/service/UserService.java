package com.hoangvu.service;

import com.hoangvu.constant.AppConstant;
import com.hoangvu.dao.UserDAO;
import com.hoangvu.dto.ResultDTO;
import com.hoangvu.entity.User;

import javax.servlet.http.HttpSession;
import java.util.Objects;

public class UserService {

    private UserDAO userDAO;
    private HttpSession httpSession;

    public UserService() {
        this.userDAO = new UserDAO();
    }

    public void setHttpSession(HttpSession httpSession) {
        this.httpSession = httpSession;
    }

    public User findByUsername(String username) {
        return this.userDAO.findByUsername(username);
    }

    public ResultDTO<User> login(User user) {
        try {
            User userFound = this.userDAO.findByUsername(user.getUsername());
            if (!userFound.getPassword().equals(user.getPassword())) {
                return new ResultDTO<>(null, true, "Incorrect password");
            }
            if (Objects.nonNull(this.httpSession))
                this.httpSession.setAttribute(AppConstant.SESSION_LOGIN_USER, userFound);
            return new ResultDTO<>(userFound, false, "Logged in successfully");
        } catch (Exception ex) {
            return new ResultDTO<>(null, true, "Login failed");
        }
    }

    public void logout() {
        if (Objects.nonNull(this.httpSession))
            this.httpSession.removeAttribute(AppConstant.SESSION_LOGIN_USER);
    }
}