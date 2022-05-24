package com.hoangvu.filter;

import com.hoangvu.constant.AppConstant;
import com.hoangvu.entity.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RoleFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute(AppConstant.SESSION_LOGIN_USER);
        if (user.getRole() != AppConstant.ROLE_ADMIN) {
            res.sendRedirect(req.getContextPath()+"/login");
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}