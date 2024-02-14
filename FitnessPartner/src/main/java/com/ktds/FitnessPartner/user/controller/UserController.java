package com.ktds.FitnessPartner.user.controller;

import com.ktds.FitnessPartner.user.dto.*;
import com.ktds.FitnessPartner.user.entity.User;
import com.ktds.FitnessPartner.user.repository.BodyInfoRepository;
import com.ktds.FitnessPartner.user.service.BodyInfoService;
import com.ktds.FitnessPartner.user.service.UserService;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @GetMapping("/join")
    public String join_page() {
        return "join";
    }

    @PostMapping("/join")
    public @ResponseBody Long join(@ModelAttribute JoinDto joinDTO) {
        return userService.join(joinDTO);
    }

    @PostMapping("/login")
    public @ResponseBody JwtResponseDto login(@ModelAttribute LoginDto loginDTO, HttpServletResponse response) {
        JwtResponseDto jwtResponseDto = JwtResponseDto.builder().token(userService.login(loginDTO)).build();
        Cookie cookie = new Cookie("token", jwtResponseDto.getToken());
        cookie.setDomain("localhost");
        cookie.setPath("/");
        cookie.setMaxAge(30*60);
        cookie.setSecure(true);
        response.addCookie(cookie);

        return jwtResponseDto;
    }
    @GetMapping("/logout")
    public @ResponseBody String logout(HttpServletResponse response) {
        Cookie cookie = new Cookie("token", null);
        cookie.setMaxAge(0);
        cookie.setDomain("localhost");
        cookie.setPath("/");
        response.addCookie(cookie);
        System.out.println("쿠키;삭제");
        return "index";
    }
    @PostMapping("/mail")
    public @ResponseBody EmailCodeDto mail(@ModelAttribute EmailRequestDto emailRequestDto) throws MessagingException, UnsupportedEncodingException {
        return EmailCodeDto.builder().code(userService.emailSend(emailRequestDto)).build();
    }
}
