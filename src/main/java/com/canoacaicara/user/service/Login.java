package com.canoacaicara.user.service;

import com.canoacaicara.user.repository.UserRepositoryAdapter;
import com.canoacaicara.security.jwt.JWTService;
import com.canoacaicara.security.jwt.TokenJWT;
import com.canoacaicara.user.service.exceptions.UserNotFoundException;
import com.canoacaicara.user.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@Slf4j
public class Login {
    private final UserRepositoryAdapter userRepositoryAdapter;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;


    public Login(UserRepositoryAdapter userRepositoryAdapter, PasswordEncoder passwordEncoder, JWTService jwtService) {
        this.userRepositoryAdapter = userRepositoryAdapter;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public TokenJWT login(String email, String password) {
        log.info("User: {} trying to log in", email);
        Optional<User> userTryingToLogIn = userRepositoryAdapter.getUser(email);

        if (userTryingToLogIn.isEmpty()) {
            throw new UserNotFoundException("User not found");
        }

        User userFound = userTryingToLogIn.get();

        if (!passwordEncoder.matches(password, userFound.password())) {
            throw new RuntimeException("Invalid Password");
        }

        String token = jwtService.generateToken(userFound);
        log.info("User: {} logged in", userFound.email());
        return new TokenJWT(token);
    }
}
