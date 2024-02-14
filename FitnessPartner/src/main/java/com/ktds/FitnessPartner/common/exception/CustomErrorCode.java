package com.ktds.FitnessPartner.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Getter
@AllArgsConstructor
public enum CustomErrorCode {
    EXPIRED_ACCESS_TOKEN(BAD_REQUEST, "JWT 토큰 만료"),
    JWT_ERROR(BAD_REQUEST, "JWT 토큰 에러"),
    ;

    private final HttpStatus httpStatus;
    private final String message;
    }
