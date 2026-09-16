package com.biz.test.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.biz.test.domain.Member;
import com.biz.test.mapper.MemberMapper;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
public class LoginController {

    private final MemberMapper memberMapper;

    private final BCryptPasswordEncoder encoder =
            new BCryptPasswordEncoder();


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

        Member member =
                memberMapper.findById(memberId);


        // 로그인 실패
        if (member == null ||
                !encoder.matches(password, member.getPassword())) {

            model.addAttribute(
                    "error",
                    "아이디 또는 비밀번호가 올바르지 않습니다."
            );

            return "login";
        }


        // 로그인 성공
        request.getSession()
                .setAttribute(
                        "loginMember",
                        member
                );

        return "redirect:/";
    }


    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {

        request.getSession()
                .invalidate();

        return "redirect:/login";
    }
}