package com.ktds.FitnessPartner.common.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<CustomErrorResponse> entity(CustomException e) {
        return make(e.getErrorCode());
    }

    public ResponseEntity<CustomErrorResponse> make(CustomErrorCode errorCode) {
        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(CustomErrorResponse.builder()
                        .status(errorCode)
                        .code(errorCode.getHttpStatus().toString())
                        .message(errorCode.getMessage())
                        .build());
    }

}
