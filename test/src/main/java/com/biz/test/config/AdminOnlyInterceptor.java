package com.biz.test.config;

import java.io.IOException;

import org.springframework.web.servlet.HandlerInterceptor;

import com.biz.test.domain.Member;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class AdminOnlyInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler) throws IOException {

        HttpSession session = request.getSession(false);

        Member member = session == null
                ? null
                : (Member) session.getAttribute("loginMember");

        if (member == null
                || !"ADMIN".equals(member.getRole())) {

            response.sendError(
                    HttpServletResponse.SC_FORBIDDEN,
                    "관리자만 접근할 수 있습니다."
            );

            return false;
        }

        return true;
    }
}
