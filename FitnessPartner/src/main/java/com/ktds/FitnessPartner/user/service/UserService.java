package com.ktds.FitnessPartner.user.service;

import com.ktds.FitnessPartner.user.dto.EmailRequestDto;
import com.ktds.FitnessPartner.user.dto.JoinDto;
import com.ktds.FitnessPartner.user.dto.LoginDto;
import com.ktds.FitnessPartner.user.entity.User;
import jakarta.mail.MessagingException;

import java.io.UnsupportedEncodingException;

public interface UserService {
    Long join(final JoinDto joinDTO);

    String login(final LoginDto loginDTO);

    String emailSend(final EmailRequestDto emailRequestDto) throws MessagingException, UnsupportedEncodingException;
}
