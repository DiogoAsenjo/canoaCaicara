package com.canoacaicara.user;

import com.canoacaicara.user.repository.persistance.UserJpaRepository;
import com.canoacaicara.security.jwt.JWTService;
import com.canoacaicara.user.service.CreateUser;
import com.canoacaicara.user.service.mapper.UserDTOMapper;
import com.canoacaicara.user.service.Login;
import com.canoacaicara.user.service.GetUser;
import com.canoacaicara.user.service.GetUsers;
import com.canoacaicara.user.repository.mapper.UserEntityMapper;
import com.canoacaicara.user.repository.UserRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class UserConfig {
    @Bean
    CreateUser createUserInteractor(PasswordEncoder passwordEncoder, UserDTOMapper userDTOMapper, UserRepositoryAdapter userRepositoryAdapter) {
        return new CreateUser(passwordEncoder, userDTOMapper, userRepositoryAdapter);
    }

    @Bean
    Login loginUserInteractor(UserRepositoryAdapter userRepositoryAdapter, PasswordEncoder passwordEncoder, JWTService jwtService) {
        return new Login(userRepositoryAdapter, passwordEncoder, jwtService);
    }

    @Bean
    GetUsers getUsersInteractor(UserRepositoryAdapter userRepositoryAdapter, UserDTOMapper userDTOMapper) {
        return new GetUsers(userRepositoryAdapter, userDTOMapper);
    }

    @Bean
    GetUser getUserInteractor(UserRepositoryAdapter userRepositoryAdapter, UserDTOMapper userDTOMapper) {
        return new GetUser(userRepositoryAdapter, userDTOMapper);
    }

    @Bean
    UserEntityMapper userEntityMapper() {
        return new UserEntityMapper();
    }

    @Bean
    UserDTOMapper UserDTOMapper() {
        return new UserDTOMapper();
    }

    @Bean
    UserRepositoryAdapter userRepositoryAdapter(UserJpaRepository userJpaRepository, UserEntityMapper userEntityMapper) {
        return new UserRepositoryAdapter(userJpaRepository, userEntityMapper);
    }
}
