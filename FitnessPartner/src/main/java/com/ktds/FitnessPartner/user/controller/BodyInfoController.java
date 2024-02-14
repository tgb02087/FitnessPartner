package com.ktds.FitnessPartner.user.controller;

import com.ktds.FitnessPartner.user.dto.BodyInfoRequestDto;
import com.ktds.FitnessPartner.user.service.BodyInfoService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/bodyInfo")
public class BodyInfoController {
    private final BodyInfoService bodyInfoService;

    @GetMapping()
    public String bodyInfoPage(HttpServletRequest request) {
        String id = request.getAttribute("id").toString();
        bodyInfoService.findById(id);
    }

    @PostMapping("/update")
    public String bodyInfo(@ModelAttribute BodyInfoRequestDto bodyInfoRequestDto, HttpServletRequest request) {
        String id = request.getAttribute("id").toString();
        bodyInfoService.save(id, bodyInfoRequestDto);
        return "mypage";
    }
}
