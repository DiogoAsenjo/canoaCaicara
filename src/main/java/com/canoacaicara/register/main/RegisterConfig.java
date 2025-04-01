package com.canoacaicara.register.main;

import com.canoacaicara.register.application.mapper.RegisterDTOMapper;
import com.canoacaicara.register.application.usecases.*;
import com.canoacaicara.register.infrastructure.gateways.RegisterEntityMapper;
import com.canoacaicara.register.infrastructure.gateways.RegisterGateway;
import com.canoacaicara.register.infrastructure.gateways.RegisterRepositoryGateway;
import com.canoacaicara.register.infrastructure.persistance.RegisterRepository;
import com.canoacaicara.security.jwt.JWTService;
import com.canoacaicara.user.infrastructure.persistance.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RegisterConfig {
    @Bean
    CreateRegisterInteractor createRegisterInteractor(RegisterGateway registerGateway, RegisterDTOMapper registerDTOMapper, JWTService jwtService) {
        return new CreateRegisterInteractor(registerGateway, registerDTOMapper, jwtService);
    }

    @Bean
    GetRegisterInteractor getRegisterInteractor(RegisterGateway registerGateway, RegisterDTOMapper registerDTOMapper, JWTService jwtService, CalculatePaymentInteractor calculatePaymentInteractor) {
        return new GetRegisterInteractor(registerGateway, registerDTOMapper, jwtService, calculatePaymentInteractor);
    }

    @Bean
    UpdateRegisterInteractor updateRegisterInteractor(RegisterGateway registerGateway, RegisterDTOMapper registerDTOMapper, JWTService jwtService) {
        return new UpdateRegisterInteractor(registerGateway, registerDTOMapper, jwtService);
    }

    @Bean
    DeleteRegisterInteractor deleteRegisterInteractor(RegisterGateway registerGateway, RegisterDTOMapper registerDTOMapper, JWTService jwtService) {
        return new DeleteRegisterInteractor(registerGateway, registerDTOMapper, jwtService);
    }

    @Bean
    CalculatePaymentInteractor calculatePaymentInteractor() {
        return new CalculatePaymentInteractor();
    }

    @Bean
    RegisterGateway registerGateway(RegisterRepository registerRepository, RegisterEntityMapper registerEntityMapper, RegisterDTOMapper registerDTOMapper) {
        return new RegisterRepositoryGateway(registerRepository, registerEntityMapper, registerDTOMapper);
    }

    @Bean
    RegisterEntityMapper registerEntityMapper(UserRepository userRepository) {
        return new RegisterEntityMapper(userRepository);
    }

    @Bean
    RegisterDTOMapper registerDTOMapper() {
        return new RegisterDTOMapper();
    }
}
