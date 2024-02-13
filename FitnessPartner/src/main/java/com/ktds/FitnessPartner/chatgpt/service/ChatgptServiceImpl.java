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
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set(config.getAuthorization(), config.getBearer() + config.getKey());

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model", config.getModel());
        requestBody.put("prompt", prompt);
        requestBody.put("max_tokens", config.getMax_token());
        requestBody.put("temperature", config.getTemperature());

        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, httpHeaders);

        RestTemplate restTemplate = new RestTemplate();
//        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
        ResponseEntity<ChatgptResponseDto> response = restTemplate.postForEntity(config.getUrl(), requestEntity, ChatgptResponseDto.class);
        System.out.println(response.getBody().getChoices().get(0).getText());
//        JSONParser jsonParser = new JSONParser();
//        JSONObject jsonObject = (JSONObject) jsonParser.parse(Objects.requireNonNull(response.getBody()).toString());
        return response.getBody().getChoices().get(0).getText();
    }
}
