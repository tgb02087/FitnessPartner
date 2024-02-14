package com.ktds.FitnessPartner.user.service;

import com.ktds.FitnessPartner.common.config.JwtProvider;
import com.ktds.FitnessPartner.common.config.MailConfig;
import com.ktds.FitnessPartner.common.exception.CustomErrorCode;
import com.ktds.FitnessPartner.common.exception.CustomException;
import com.ktds.FitnessPartner.user.dto.EmailRequestDto;
import com.ktds.FitnessPartner.user.dto.JoinDto;
import com.ktds.FitnessPartner.user.dto.LoginDto;
import com.ktds.FitnessPartner.user.entity.User;
import com.ktds.FitnessPartner.user.repository.UserRepository;
import com.sun.jdi.request.DuplicateRequestException;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.security.SecureRandom;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    private final JavaMailSender javaMailSender;

    private final MailConfig mailConfig;

    private final JwtProvider jwtProvider;

    @Override
    @Transactional
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
    public String login(final LoginDto loginDTO) {
        User user = userRepository.findByEmailAndPassword(loginDTO.getEmail(), loginDTO.getPassword());
        String token = "";
        if(user!=null) {
            token = jwtProvider.createToken(user);
            System.out.println("token = " + token);
        }
        else {
            throw new CustomException(CustomErrorCode.JWT_ERROR);
        }
        return token;
    }

    @Override
    public String emailSend(final EmailRequestDto emailRequestDto) throws MessagingException, UnsupportedEncodingException {
        User user = userRepository.findByEmail(emailRequestDto.getEmail());
        if(user!=null) {
            throw new DuplicateRequestException();
        }
        String code = createRandomCode();
        javaMailSender.send(createMessage(emailRequestDto.getEmail(), code));
        return code;
    }
    private MimeMessage createMessage(final String email, final String code) throws UnsupportedEncodingException, MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        message.addRecipients(Message.RecipientType.TO, email);
        message.setSubject(mailConfig.getSUBJECT());
        message.setText(mailConfig.getMESSAGE_FRONT() + code + mailConfig.getMESSAGE_BACK(), "UTF-8", "html");
        message.setFrom(new InternetAddress(mailConfig.getServerEmail(), "FitnessPartner"));
        return message;
    }

    private String createRandomCode() {
        final String temp = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        StringBuilder sb = new StringBuilder();
        SecureRandom random = new SecureRandom();

        for (int i = 0; i < 6; i++) {
            sb.append(temp.charAt(random.nextInt(temp.length())));
        }

        return sb.toString();
    }
}
