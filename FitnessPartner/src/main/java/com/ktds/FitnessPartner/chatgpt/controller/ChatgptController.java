package com.ktds.FitnessPartner.chatgpt.controller;

import com.ktds.FitnessPartner.chatgpt.dto.ChatgptDto;
import com.ktds.FitnessPartner.chatgpt.service.ChatgptService;
import lombok.RequiredArgsConstructor;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/chat-gpt")
public class ChatgptController {
    private final ChatgptService chatgptService;

    @PostMapping("/test")
    public @ResponseBody ChatgptDto test(@ModelAttribute ChatgptDto chatgptDTO) throws ParseException {
        String msg = "오늘 하루 식단을 "+chatgptDTO.getPrompt()+"Kcal에 맞게 추천해줘.";
        return ChatgptDto.builder().prompt(chatgptService.generateText(msg)).build();
    }

}
