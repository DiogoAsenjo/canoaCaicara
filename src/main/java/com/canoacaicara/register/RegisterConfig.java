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
    CreateRegister createRegisterInteractor(RegisterRepositoryAdapter registerRepositoryAdapter, RegisterDTOMapper registerDTOMapper, JWTService jwtService) {
        return new CreateRegister(registerRepositoryAdapter, registerDTOMapper, jwtService);
    }

    @Bean
    GetRegister getRegisterInteractor(RegisterRepositoryAdapter registerRepositoryAdapter, RegisterDTOMapper registerDTOMapper, JWTService jwtService, CalculatePayment calculatePayment) {
        return new GetRegister(registerRepositoryAdapter, registerDTOMapper, jwtService, calculatePayment);
    }

    @Bean
    UpdateRegister updateRegisterInteractor(RegisterRepositoryAdapter registerRepositoryAdapter, RegisterDTOMapper registerDTOMapper, JWTService jwtService) {
        return new UpdateRegister(registerRepositoryAdapter, registerDTOMapper, jwtService);
    }

    @Bean
    DeleteRegister deleteRegisterInteractor(RegisterRepositoryAdapter registerRepositoryAdapter, RegisterDTOMapper registerDTOMapper, JWTService jwtService) {
        return new DeleteRegister(registerRepositoryAdapter, registerDTOMapper, jwtService);
    }

    @Bean
    CalculatePayment calculatePaymentInteractor() {
        return new CalculatePayment();
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
