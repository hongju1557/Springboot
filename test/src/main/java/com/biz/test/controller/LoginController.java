
package com.biz.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.biz.test.domain.Member;
import com.biz.test.service.LoginService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String login(
            @RequestParam("memberId") String memberId,
            @RequestParam("password") String password,
            HttpServletRequest request,
            Model model) {

        Member member;

        try {
            member = loginService.login(memberId, password);
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "login";
        }

        HttpSession session = request.getSession();
        request.changeSessionId();

        // 세션에는 비밀번호 대신 화면과 권한 검사에 필요한 정보만 저장
        Member loginMember = Member.builder()
                .memberId(member.getMemberId())
                .memberName(member.getMemberName())
                .role(member.getRole())
                .build();

        session.setAttribute("loginMember", loginMember);

        return "redirect:/";
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {

        HttpSession session = request.getSession(false);

        if (session != null) {
            session.invalidate();
        }

        return "redirect:/login";
    }
}