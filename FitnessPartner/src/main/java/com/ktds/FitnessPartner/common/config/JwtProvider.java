package com.ktds.FitnessPartner.common.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.ktds.FitnessPartner.common.exception.CustomErrorCode;
import com.ktds.FitnessPartner.common.exception.CustomException;
import com.ktds.FitnessPartner.user.entity.User;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

import static com.ktds.FitnessPartner.common.exception.CustomErrorCode.JWT_ERROR;

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

    public String validateToken(String jwtToken) {
        System.out.println("토큰 체크");
        try {
            if (JWT.decode(jwtToken).getExpiresAt().before(new Date())) {
//                throw new AuthException(AuthErrorResult.EXPIRED_ACCESS_TOKEN);
                throw new CustomException(CustomErrorCode.EXPIRED_ACCESS_TOKEN);
            }
            return JWT.require(Algorithm.HMAC512(jwtConfig.getSecretKey()))
                    .build()
                    .verify(jwtToken)
                    .getClaim(jwtConfig.getEMAIL_KEY())
                    .asString();
        } catch (CustomException e) {
            e.getStackTrace();
            throw new CustomException(JWT_ERROR);
        }
    }

    public String getTokenId(String token) {
        return JWT.decode(token).getSubject();
    }

    public String getToken(HttpServletRequest request) {
        String token = "";
        Cookie[] list = request.getCookies();
        for(Cookie cookie:list) {
            if(cookie.getName().equals("token")) {
                token = cookie.getValue();
                break;
            }
        }
        return token;
    }

}
