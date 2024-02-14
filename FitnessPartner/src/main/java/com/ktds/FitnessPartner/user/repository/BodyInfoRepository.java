package com.ktds.FitnessPartner.user.repository;

import com.ktds.FitnessPartner.user.entity.BodyInfo;
import com.ktds.FitnessPartner.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BodyInfoRepository extends JpaRepository<BodyInfo, Long> {

    BodyInfo findByUserId(User user);
}
