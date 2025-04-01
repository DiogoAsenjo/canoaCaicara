package com.canoacaicara.security.jwt;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class EndpointsSecurityConfig {
    private final AuthenticationFilter authenticationFilter;

    public static final String[] ENDPOINTS_AUTH_NOT_REQUIRED = {
            "/users",
            "/users/login",
    };

    public static final String[] ENDPOINTS_ADMIN = {
        "/users/admin",
        "/registers/all"
    };

    public EndpointsSecurityConfig(AuthenticationFilter authenticationFilter) {
        this.authenticationFilter = authenticationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST, ENDPOINTS_AUTH_NOT_REQUIRED).permitAll()
                        .requestMatchers(ENDPOINTS_ADMIN).hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
