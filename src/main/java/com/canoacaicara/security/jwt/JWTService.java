package com.canoacaicara.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.canoacaicara.user.User;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;

public class JWTService {
    @Value("${jwt.secret-key}")
    private String SECRET_KEY;

    @Value("${jwt.expiration-time}")
    private long EXPIRATION_TIME;

    public String generateToken(User user) {
        return JWT.create()
                .withSubject(user.email())
                .withClaim("userId", user.id())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(Algorithm.HMAC256(SECRET_KEY));
    }

    public boolean isValidToken(String token) {
        try {
            JWT.require(Algorithm.HMAC256(SECRET_KEY)).build().verify(token);
            return true;
        }catch (Exception e) {
            return false;
        }
    }

    public String getEmailFromToken(String token) {
        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256(SECRET_KEY)).build().verify(token);

        return decodedJWT.getSubject();
    }

    public int getUserIdFromToken(String token) {
        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256(SECRET_KEY)).build().verify(token);

        return decodedJWT.getClaim("userId").asInt();
    }

    public String clearToken (String bearerToken) {
        if (!bearerToken.startsWith("Bearer")) {
            throw new RuntimeException("Token in the wrong format");
        }
        return bearerToken.substring(7);
    }
}
