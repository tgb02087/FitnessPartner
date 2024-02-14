package com.ktds.FitnessPartner.user.service;

import com.ktds.FitnessPartner.user.dto.BodyInfoRequestDto;
import com.ktds.FitnessPartner.user.dto.BodyResponseDto;
import com.ktds.FitnessPartner.user.entity.BodyInfo;
import com.ktds.FitnessPartner.user.entity.User;
import com.ktds.FitnessPartner.user.repository.BodyInfoRepository;
import com.ktds.FitnessPartner.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BodyInfoServiceImpl implements BodyInfoService{
    private final BodyInfoRepository bodyInfoRepository;
    private final UserRepository userRepository;
    @Override
    @Transactional
    public void save(String id, BodyInfoRequestDto bodyInfoRequestDto) {
        User user = userRepository.findById(Long.valueOf(id)).orElse(null);
        BodyInfo bodyInfo = BodyInfo.builder()
                .userId(user)
                .weight((bodyInfoRequestDto.getHeight()-100)*0.9)
                .height(bodyInfoRequestDto.getHeight())
                .activity(bodyInfoRequestDto.getActivity())
                .createTime(LocalDateTime.now())
                .build();
        bodyInfoRepository.save(bodyInfo);
    }

    @Override
    public BodyResponseDto findById(String id) {
        BodyInfo bodyInfo = bodyInfoRepository.findById(Long.valueOf(id)).orElse(null);
        return BodyResponseDto.builder()
                .weight(bodyInfo.getWeight())
                .height(bodyInfo.getHeight())
                .activity(bodyInfo.getActivity())
                .kcal((int)(bodyInfo.getWeight() * bodyInfo.getActivity()))
                .build();
    }
}
