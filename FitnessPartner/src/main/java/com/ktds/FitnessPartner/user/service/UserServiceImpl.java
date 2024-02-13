package com.ktds.FitnessPartner.user.service;

import com.ktds.FitnessPartner.user.dto.JoinDto;
import com.ktds.FitnessPartner.user.dto.LoginDto;
import com.ktds.FitnessPartner.user.entity.User;
import com.ktds.FitnessPartner.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    @Override
    public Long join(final JoinDto joinDTO) {
        User user = User.builder()
                .email(joinDTO.getEmail())
                .password(joinDTO.getPassword())
                .nickname("닉네임")
                .createTime(LocalDateTime.now())
                .build();
        return userRepository.save(user).getId();
    }

    @Override
    public User login(final LoginDto loginDTO) {
        return userRepository.findByEmailAndPassword(loginDTO.getEmail(), loginDTO.getPassword());
    }
}
