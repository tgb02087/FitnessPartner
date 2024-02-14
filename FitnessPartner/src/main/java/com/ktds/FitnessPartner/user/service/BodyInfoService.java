package com.ktds.FitnessPartner.user.service;

import com.ktds.FitnessPartner.user.dto.BodyInfoRequestDto;
import com.ktds.FitnessPartner.user.dto.BodyResponseDto;

public interface BodyInfoService {

    void save(String id, BodyInfoRequestDto bodyInfoRequestDto);

    BodyResponseDto findById(String id);
}
