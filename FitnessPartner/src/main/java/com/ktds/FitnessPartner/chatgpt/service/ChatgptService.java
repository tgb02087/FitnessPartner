package com.ktds.FitnessPartner.chatgpt.service;

import com.ktds.FitnessPartner.chatgpt.dto.ChatgptResponseDto;
import org.json.simple.parser.ParseException;

public interface ChatgptService {
    String generateText(final String prompt) throws ParseException;
}
