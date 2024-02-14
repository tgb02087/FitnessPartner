package com.ktds.FitnessPartner.board.entity;

import com.ktds.FitnessPartner.common.entity.CommonEntity;

import com.ktds.FitnessPartner.user.entity.User;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Table(name = "boards")
@AttributeOverride(name = "id", column = @Column(name = "board_id"))
public class Board extends CommonEntity{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User userId;

    private String title;

    private String content;

    private String image;
}
