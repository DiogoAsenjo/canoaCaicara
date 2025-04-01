package com.canoacaicara.user.application.usecases;

import com.canoacaicara.security.jwt.JWTService;
import com.canoacaicara.security.jwt.TokenJWT;
import com.canoacaicara.user.application.exceptions.UserNotFoundException;
import com.canoacaicara.user.domain.User;
import com.canoacaicara.user.infrastructure.gateways.UserGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@Slf4j
public class LoginUserInteractor {
    private final UserGateway userGateway;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;


    public LoginUserInteractor(UserGateway userGateway, PasswordEncoder passwordEncoder, JWTService jwtService) {
        this.userGateway = userGateway;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public TokenJWT login(String email, String password) {
        log.info("User: {} trying to log in", email);
        Optional<User> userTryingToLogIn = userGateway.getUser(email);

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
