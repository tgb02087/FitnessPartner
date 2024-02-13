package com.ktds.FitnessPartner.chatgpt.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
public class ChatgptDto {
    private String prompt;
}
