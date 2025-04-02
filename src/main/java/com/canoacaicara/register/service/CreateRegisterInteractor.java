package com.canoacaicara.register.service;

import com.canoacaicara.register.repository.RegisterRepositoryAdapter;
import com.canoacaicara.register.service.exceptions.RegisterManipulationException;
import com.canoacaicara.register.service.mapper.RegisterDTOMapper;
import com.canoacaicara.register.Register;
import com.canoacaicara.register.controller.dto.CreateRegisterRequest;
import com.canoacaicara.register.controller.dto.RegisterResponse;
import com.canoacaicara.security.jwt.JWTService;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class CreateRegisterInteractor {
    private final RegisterRepositoryAdapter registerRepositoryAdapter;
    private final RegisterDTOMapper registerDTOMapper;
    private final JWTService jwtService;

    public CreateRegisterInteractor(RegisterRepositoryAdapter registerRepositoryAdapter, RegisterDTOMapper registerDTOMapper, JWTService jwtService) {
        this.registerRepositoryAdapter = registerRepositoryAdapter;
        this.registerDTOMapper = registerDTOMapper;
        this.jwtService = jwtService;
    }

    public RegisterResponse createRegister(CreateRegisterRequest request, String token) throws Exception {
        String clearToken = jwtService.clearToken(token);
        int userId = jwtService.getUserIdFromToken(clearToken);
        Register registerDomain = registerDTOMapper.toRegister(request, userId);

        List<Register> registerWithTheSameDateAndType = registerRepositoryAdapter.getUserRegisterByDateAndType(userId, request.date(), request.activityType());

        if (!registerWithTheSameDateAndType.isEmpty()) {
            throw new RegisterManipulationException("You already have a register of this type in this date!");
        }

        try {
            log.info("User: {} trying to create a new Register", jwtService.getEmailFromToken(clearToken));
            Register registerCreated = registerRepositoryAdapter.createRegister(registerDomain);
            log.info("User: {} created a new Register with id: {}", jwtService.getEmailFromToken(clearToken), registerCreated.id());
            return registerDTOMapper.toResponse(registerCreated);
        } catch (Exception e) {
            log.error("User: {} had a problem trying to create a new Register. {}", jwtService.getEmailFromToken(clearToken), e.toString());
            throw new Exception(e.getMessage());
        }
    }
}
