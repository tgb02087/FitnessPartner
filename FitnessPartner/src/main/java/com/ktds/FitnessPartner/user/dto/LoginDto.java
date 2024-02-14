package com.ktds.FitnessPartner.user.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class LoginDto {
    private String email;
    private String password;
}
