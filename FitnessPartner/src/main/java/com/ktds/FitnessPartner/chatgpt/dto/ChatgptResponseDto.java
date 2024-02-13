package com.ktds.FitnessPartner.chatgpt.dto;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ChatgptResponseDto implements Serializable {
    private String id;
    private String object;
    private LocalDate created;
    private String model;
    private List<ChoiceDto> choices;
}
