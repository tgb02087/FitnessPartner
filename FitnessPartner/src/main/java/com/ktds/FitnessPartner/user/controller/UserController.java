package com.ktds.FitnessPartner.user.controller;

import com.ktds.FitnessPartner.user.dto.*;
import com.ktds.FitnessPartner.user.entity.User;
import com.ktds.FitnessPartner.user.service.UserService;
import jakarta.mail.MessagingException;
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
    public @ResponseBody JwtResponseDto login(@ModelAttribute LoginDto loginDTO) {
        JwtResponseDto jwtResponseDto = JwtResponseDto.builder().token(userService.login(loginDTO)).build();
        return jwtResponseDto;
    }
    @PostMapping("/mail")
    public @ResponseBody EmailCodeDto mail(@ModelAttribute EmailRequestDto emailRequestDto) throws MessagingException, UnsupportedEncodingException {
        return EmailCodeDto.builder().code(userService.emailSend(emailRequestDto)).build();
    }
}
