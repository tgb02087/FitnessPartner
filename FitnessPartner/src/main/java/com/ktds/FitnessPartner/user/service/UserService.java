package com.ktds.FitnessPartner.user.service;

import com.ktds.FitnessPartner.user.dto.JoinDto;
import com.ktds.FitnessPartner.user.dto.LoginDto;
import com.ktds.FitnessPartner.user.entity.User;

public interface UserService {
    Long join(final JoinDto joinDTO);

    User login(final LoginDto loginDTO);
}
