package com.hoangvu.filter;

import com.hoangvu.constant.AppConstant;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

public class AuthenticationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession();
        Object user = session.getAttribute(AppConstant.SESSION_LOGIN_USER);
        if (Objects.nonNull(user)) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        resp.sendRedirect(req.getContextPath()+"/login");
    }
}