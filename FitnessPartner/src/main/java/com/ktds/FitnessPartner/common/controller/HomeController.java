package com.ktds.FitnessPartner.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String index() {
        return "index";
    }
    @GetMapping("/home")
    public String home() {
        return "home";
    }
    @GetMapping("/food_plan")
    public String food_plan() {
        return "food_plan";
    }
    @GetMapping("/exercise_plan")
    public String exercise_plan() {
        return "exercise_plan";
    }
    @GetMapping("/mypage")
    public String mypage() {
        return "mypage";
    }
}
