package com.ktds.FitnessPartner.user.service;

import com.ktds.FitnessPartner.user.dto.JoinDTO;
import com.ktds.FitnessPartner.user.dto.LoginDTO;
import com.ktds.FitnessPartner.user.entity.User;

public interface UserService {
    Long join(final JoinDTO joinDTO);

    User login(final LoginDTO loginDTO);
}
