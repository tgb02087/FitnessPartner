package com.ktds.FitnessPartner.common.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ktds.FitnessPartner.common.config.JwtProvider;
import com.ktds.FitnessPartner.common.exception.CustomErrorCode;
import com.ktds.FitnessPartner.common.exception.CustomErrorResponse;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class Myfilter extends OncePerRequestFilter {
    private final JwtProvider jwtProvider;

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String[] excludePath = {
                "/css",
                "/user",
                "/img"
        };

        String path = request.getRequestURI();
        if(path.equals("/")) {
            return true;
        }
        return Arrays.stream(excludePath).anyMatch(path::startsWith);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("filter 적용");
        //logger.info(request.getRequestURI());
        String token = jwtProvider.getToken(request);
        //        jwtProvider.validateToken(token);
        request.setAttribute("id", jwtProvider.getTokenId(token));

        filterChain.doFilter(request, response);
    }

//    private void setErrorResponse(HttpServletResponse response, CustomErrorCode errorCode) {
//        ObjectMapper objectMapper = new ObjectMapper();
//        response.setStatus(errorCode.getHttpStatus().value());
//        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
//        CustomErrorResponse errorResponse = CustomErrorResponse.builder()
//                .status(errorCode)
//                .code(errorCode.getHttpStatus().toString())
//                .message(errorCode.getMessage())
//                .build();
//        try{
//            response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
//        }catch (IOException e){
//            e.printStackTrace();
//        }
//    }
}
