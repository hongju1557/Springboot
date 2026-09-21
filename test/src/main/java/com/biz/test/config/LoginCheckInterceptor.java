package com.biz.test.config;

import java.io.IOException;

import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LoginCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler) throws IOException {

        // 기존 세션만 조회: 세션이 없으면 null
        HttpSession session = request.getSession(false);

        // 세션이 없거나 로그인 정보가 없으면 접근 차단
        if (session == null
                || session.getAttribute("loginMember") == null) {

            response.sendRedirect(
                    request.getContextPath() + "/login"
            );

            return false;
        }

        // 로그인한 사용자라면 컨트롤러 실행
        return true;
    }
}
