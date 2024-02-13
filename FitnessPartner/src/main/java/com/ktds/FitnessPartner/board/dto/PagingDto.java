package com.ktds.FitnessPartner.board.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class PagingDto {
    private int currentPage;
    private int startPage;
    private int endPage;
}
