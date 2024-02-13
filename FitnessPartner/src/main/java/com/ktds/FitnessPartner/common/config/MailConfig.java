package com.ktds.FitnessPartner.common.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class MailConfig {
    @Value("${spring.mail.port}")
    private String serverPort;

    @Value("${spring.mail.host}")
    private String serverHost;

    @Value("${spring.mail.username}")
    private String serverEmail;

    @Value("${spring.mail.password}")
    private String serverPassword;

    private final String SUBJECT = "Fitness Partner 회원가입 인증 코드";
    private final String MESSAGE_FRONT = "<div style='margin:10;'> <div align='center' style='border:1px solid black; font-family:verdana';> <h3 style='color:blue;'>회원가입 코드입니다.</h3> <div style='font-size:130%'> CODE : <strong>";
    private final String MESSAGE_BACK = "</strong><div><br/> </div>";
}
