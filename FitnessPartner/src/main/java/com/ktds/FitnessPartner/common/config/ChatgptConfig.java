package com.ktds.FitnessPartner.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ChatgptConfig {
    @Value("${AUTHORIZATION}")
    public String authorization;

    @Value("${BEARER}")
    public String bearer;

    @Value("${API_KEY}")
    public String key;

    @Value("${MODEL}")
    public String model;

    @Value("${MAX_TOKEN}")
    public Integer max_token;

    @Value("${TEMPERATURE}")
    public Double temperature;

    @Value("${TOP_P}")
    public Double topp;

    @Value("${URL}")
    public String url;
}
