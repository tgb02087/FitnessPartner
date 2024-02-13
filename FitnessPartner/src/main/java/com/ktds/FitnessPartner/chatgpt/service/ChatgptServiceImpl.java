package com.ktds.FitnessPartner.chatgpt.service;

import com.ktds.FitnessPartner.chatgpt.dto.ChatgptResponseDto;
import com.ktds.FitnessPartner.common.config.ChatgptConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ChatgptServiceImpl implements ChatgptService {
    private final ChatgptConfig config;
    @Override
    public String generateText(final String prompt) {
        System.out.println(config.key);
        System.out.println(config.authorization);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set(config.authorization, config.bearer + config.key);

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model", config.model);
        requestBody.put("prompt", prompt);
        requestBody.put("max_tokens", config.max_token);
        requestBody.put("temperature", config.temperature);
//        requestBody.put("topP", config.topp);

        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, httpHeaders);

        RestTemplate restTemplate = new RestTemplate();
//        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
        ResponseEntity<ChatgptResponseDto> response = restTemplate.postForEntity(config.url, requestEntity, ChatgptResponseDto.class);
        System.out.println(response.getBody().getChoices().get(0).getText());
//        JSONParser jsonParser = new JSONParser();
//        JSONObject jsonObject = (JSONObject) jsonParser.parse(Objects.requireNonNull(response.getBody()).toString());
        return response.getBody().getChoices().get(0).getText();
    }
}
