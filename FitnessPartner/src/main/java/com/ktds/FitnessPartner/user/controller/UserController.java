package com.ktds.FitnessPartner.user.controller;

import com.ktds.FitnessPartner.user.dto.Email;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
    @GetMapping("/join")
    public String join_page() {
        return "join";
    }

    @PostMapping("/join")
    public String join(@ModelAttribute Email email) {
        System.out.println(email.getEmail());
        return "join";
    }
}
