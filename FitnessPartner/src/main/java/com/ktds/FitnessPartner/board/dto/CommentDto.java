package com.ktds.FitnessPartner.board.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
public class CommentDto {
    private Long boardId;
    private String comments;
}
