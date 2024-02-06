package com.ktds.FitnessPartner.user.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class JoinDTO {
    private String email;
    private String password;
    private String nickname;
}
