package com.ktds.FitnessPartner.common.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.ktds.FitnessPartner.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtProvider {
    private final JwtConfig jwtConfig;

    public String createToken(final User user) {
        return createAccessToken(user);
    }
    private String createAccessToken(final User user) {
        return JWT.create()
                .withSubject(user.getId().toString())
                .withExpiresAt(new Date(System.currentTimeMillis() + jwtConfig.getExpires()))
                .withClaim(jwtConfig.getNICKNAME_KEY(), user.getNickname())
                .withClaim(jwtConfig.getEMAIL_KEY(), user.getEmail())
                .sign(Algorithm.HMAC512(jwtConfig.getSecretKey()));
    }
}
