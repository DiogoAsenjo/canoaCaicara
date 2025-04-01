package com.canoacaicara.security;

import com.canoacaicara.security.jwt.AuthenticationFilter;
import com.canoacaicara.security.jwt.JWTService;
import com.canoacaicara.security.jwt.UserDetailsServiceImpl;
import com.canoacaicara.user.infrastructure.persistance.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    JWTService jwtService() {
        return new JWTService();
    }

    @Bean
    AuthenticationFilter authenticationFilter(JWTService jwtService, UserDetailsServiceImpl userDetailsServiceImpl) {
        return  new AuthenticationFilter(jwtService, userDetailsServiceImpl);
    }

    @Bean
    UserDetailsServiceImpl userDetailsService(UserRepository userRepository) {
        return new UserDetailsServiceImpl(userRepository);
    }
}
