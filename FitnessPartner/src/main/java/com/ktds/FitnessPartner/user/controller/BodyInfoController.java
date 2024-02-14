package com.ktds.FitnessPartner.user.controller;

import com.ktds.FitnessPartner.user.dto.BodyInfoRequestDto;
import com.ktds.FitnessPartner.user.dto.BodyResponseDto;
import com.ktds.FitnessPartner.user.service.BodyInfoService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/bodyInfo")
public class BodyInfoController {
    private final BodyInfoService bodyInfoService;

    @GetMapping()
    public String bodyInfoPage(HttpServletRequest request, Model model) {
        String id = request.getAttribute("id").toString();
        BodyResponseDto bodyResponseDto = bodyInfoService.findById(id);
        model.addAttribute("body", bodyResponseDto);
        model.addAttribute("pageNum", 5);
        return "mypage";
    }

    @PostMapping("/update")
    public String bodyInfo(@ModelAttribute BodyInfoRequestDto bodyInfoRequestDto, HttpServletRequest request, Model model) {
        String id = request.getAttribute("id").toString();
        BodyResponseDto bodyResponseDto = bodyInfoService.save(id, bodyInfoRequestDto);
        model.addAttribute("body", bodyResponseDto);
        model.addAttribute("pageNum", 5);
        return "mypage";
    }
}
