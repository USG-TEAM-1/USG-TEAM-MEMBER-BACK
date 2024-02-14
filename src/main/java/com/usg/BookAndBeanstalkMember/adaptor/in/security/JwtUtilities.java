package com.usg.BookAndBeanstalkMember.adaptor.in.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Slf4j
@Component
public class JwtUtilities {
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long jwtExpiration;

    public String generateToken(String email) {
        System.out.println("== generateToken 진입");
        Date accessTokenExp = new Date(System.currentTimeMillis() + (60000 * 600));

        return JWT.create()
                .withSubject("Member")
                .withClaim("email", "donghyeon09@naver.com")
                .withExpiresAt(accessTokenExp)
                .sign(Algorithm.HMAC512(secret));
    }
}
