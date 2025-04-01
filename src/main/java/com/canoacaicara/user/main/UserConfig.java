package com.canoacaicara.user.main;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.canoacaicara.groups.usecases.EnrollUserInteractor;
import com.canoacaicara.security.jwt.JWTService;
import com.canoacaicara.user.application.mapper.UserDTOMapper;
import com.canoacaicara.user.application.usecases.CreateUserInteractor;
import com.canoacaicara.user.application.usecases.GetUserInteractor;
import com.canoacaicara.user.application.usecases.GetUsersInteractor;
import com.canoacaicara.user.application.usecases.LoginUserInteractor;
import com.canoacaicara.user.infrastructure.gateways.UserEntityMapper;
import com.canoacaicara.user.infrastructure.gateways.UserGateway;
import com.canoacaicara.user.infrastructure.gateways.UserRepositoryGateway;
import com.canoacaicara.user.infrastructure.persistance.UserRepository;


@Configuration
public class UserConfig {
    @Bean
    CreateUserInteractor createUserCase(UserGateway userGateway, PasswordEncoder passwordEncoder, EnrollUserInteractor enrollUserInteractor, UserDTOMapper userDTOMapper) {
        return new CreateUserInteractor(userGateway, passwordEncoder, enrollUserInteractor, userDTOMapper);
    }

    @Bean
    LoginUserInteractor loginUserInteractor(UserGateway userGateway, PasswordEncoder passwordEncoder, JWTService jwtService) {
        return new LoginUserInteractor(userGateway, passwordEncoder, jwtService);
    }

    @Bean
    GetUsersInteractor getUsersInteractor(UserGateway userGateway, UserDTOMapper userDTOMapper) {
        return new GetUsersInteractor(userGateway, userDTOMapper);
    }

    @Bean
    GetUserInteractor getUserInteractor(UserGateway userGateway, UserDTOMapper userDTOMapper) {
        return new GetUserInteractor(userGateway, userDTOMapper);
    }

    @Bean
    UserGateway userGateway(UserRepository userRepository, UserEntityMapper userEntityMapper) {
        return new UserRepositoryGateway(userRepository, userEntityMapper);
    }

    @Bean
    UserEntityMapper userEntityMapper() {
        return new UserEntityMapper();
    }

    @Bean
    UserDTOMapper UserDTOMapper() {
        return new UserDTOMapper();
    }
}
