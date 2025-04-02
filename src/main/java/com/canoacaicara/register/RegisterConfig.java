package com.canoacaicara.register;

import com.canoacaicara.register.repository.persistance.RegisterJpaRepository;
import com.canoacaicara.register.service.*;
import com.canoacaicara.register.service.mapper.RegisterDTOMapper;
import com.canoacaicara.register.repository.mapper.RegisterEntityMapper;
import com.canoacaicara.register.repository.RegisterRepositoryAdapter;
import com.canoacaicara.security.jwt.JWTService;
import com.canoacaicara.user.repository.persistance.UserJpaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RegisterConfig {
    @Bean
    CreateRegisterInteractor createRegisterInteractor(RegisterRepositoryAdapter registerRepositoryAdapter, RegisterDTOMapper registerDTOMapper, JWTService jwtService) {
        return new CreateRegisterInteractor(registerRepositoryAdapter, registerDTOMapper, jwtService);
    }

    @Bean
    GetRegisterInteractor getRegisterInteractor(RegisterRepositoryAdapter registerRepositoryAdapter, RegisterDTOMapper registerDTOMapper, JWTService jwtService, CalculatePaymentInteractor calculatePaymentInteractor) {
        return new GetRegisterInteractor(registerRepositoryAdapter, registerDTOMapper, jwtService, calculatePaymentInteractor);
    }

    @Bean
    UpdateRegisterInteractor updateRegisterInteractor(RegisterRepositoryAdapter registerRepositoryAdapter, RegisterDTOMapper registerDTOMapper, JWTService jwtService) {
        return new UpdateRegisterInteractor(registerRepositoryAdapter, registerDTOMapper, jwtService);
    }

    @Bean
    DeleteRegisterInteractor deleteRegisterInteractor(RegisterRepositoryAdapter registerRepositoryAdapter, RegisterDTOMapper registerDTOMapper, JWTService jwtService) {
        return new DeleteRegisterInteractor(registerRepositoryAdapter, registerDTOMapper, jwtService);
    }

    @Bean
    CalculatePaymentInteractor calculatePaymentInteractor() {
        return new CalculatePaymentInteractor();
    }

    @Bean
    RegisterEntityMapper registerEntityMapper(UserJpaRepository userJpaRepository) {
        return new RegisterEntityMapper(userJpaRepository);
    }

    @Bean
    RegisterDTOMapper registerDTOMapper() {
        return new RegisterDTOMapper();
    }

    @Bean
    RegisterRepositoryAdapter registerRepositoryAdapter(RegisterJpaRepository registerJpaRepository, RegisterEntityMapper registerEntityMapper, RegisterDTOMapper registerDTOMapper) {
        return new RegisterRepositoryAdapter(registerJpaRepository, registerEntityMapper, registerDTOMapper);
    }
}
