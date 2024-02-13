package com.ktds.FitnessPartner.user.controller;

import com.ktds.FitnessPartner.user.dto.JoinDto;
import com.ktds.FitnessPartner.user.dto.LoginDto;
import com.ktds.FitnessPartner.user.entity.User;
import com.ktds.FitnessPartner.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    public @ResponseBody User login(@ModelAttribute LoginDto loginDTO) {
        System.out.println(loginDTO.getEmail());
        return userService.login(loginDTO);
    }
}
