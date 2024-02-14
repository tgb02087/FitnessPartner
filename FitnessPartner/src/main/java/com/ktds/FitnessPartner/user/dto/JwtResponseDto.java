package com.ktds.FitnessPartner.user.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
public class JwtResponseDto {
    private String token;
}
