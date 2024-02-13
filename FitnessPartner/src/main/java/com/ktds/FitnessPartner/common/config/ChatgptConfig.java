package com.ktds.FitnessPartner.common.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class ChatgptConfig {
    @Value("${AUTHORIZATION}")
    private String authorization;

    @Value("${BEARER}")
    private String bearer;

    @Value("${API_KEY}")
    private String key;

    @Value("${MODEL}")
    private String model;

    @Value("${MAX_TOKEN}")
    private Integer max_token;

    @Value("${TEMPERATURE}")
    private Double temperature;

    @Value("${TOP_P}")
    private Double topp;

    @Value("${URL}")
    private String url;
}
