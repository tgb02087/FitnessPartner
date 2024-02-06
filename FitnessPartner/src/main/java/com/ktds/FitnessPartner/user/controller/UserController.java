package com.ktds.FitnessPartner.user.controller;

import com.ktds.FitnessPartner.user.dto.JoinDTO;
import com.ktds.FitnessPartner.user.dto.LoginDTO;
import com.ktds.FitnessPartner.user.entity.User;
import com.ktds.FitnessPartner.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public @ResponseBody Long join(@ModelAttribute JoinDTO joinDTO) {
        return userService.join(joinDTO);
    }

    @PostMapping("/login")
    public @ResponseBody User login(@ModelAttribute LoginDTO loginDTO) {
        System.out.println(loginDTO.getEmail());
        return userService.login(loginDTO);
    }
}
