package com.anything.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("LoginInterceptor : preHandle IN!!!!");
        if (request.getSession().getAttribute("member") == null) {
            log.info("LoginInterceptor : NO SESSION!!!");
            response.sendRedirect("/login/index");
            return false;
        }
        return true;
    }
}
