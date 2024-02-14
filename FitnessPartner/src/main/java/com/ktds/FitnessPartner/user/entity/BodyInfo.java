package com.ktds.FitnessPartner.user.entity;

import com.ktds.FitnessPartner.common.entity.CommonEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.ColumnDefault;

@SuperBuilder
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Table(name = "body_info")
@AttributeOverride(name = "id", column = @Column(name = "body_info_id"))
public class BodyInfo extends CommonEntity {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User userId;

    @ColumnDefault("0")
    private Double weight;

    @ColumnDefault("0")
    private Double height;

    @ColumnDefault("0")
    private Integer activity;
}
