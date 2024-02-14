package com.ktds.FitnessPartner.user.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BodyResponseDto {
    private Long id;
    private Double weight;
    private Double height;
    private Integer activity;
    private Integer kcal;
}
