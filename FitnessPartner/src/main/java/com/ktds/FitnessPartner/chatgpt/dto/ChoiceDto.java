package com.ktds.FitnessPartner.chatgpt.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ChoiceDto implements Serializable {
    private String text;
    private Integer index;
    @JsonProperty("finish_reason")
    private String finishReason;
}
