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
public class UpdateRegisterInteractor {
    private final RegisterRepositoryAdapter registerRepositoryAdapter;
    private final RegisterDTOMapper registerDTOMapper;
    private final JWTService jwtService;

    public UpdateRegisterInteractor(RegisterRepositoryAdapter registerRepositoryAdapter, RegisterDTOMapper registerDTOMapper, JWTService jwtService) {
        this.registerRepositoryAdapter = registerRepositoryAdapter;
        this.registerDTOMapper = registerDTOMapper;
        this.jwtService = jwtService;
    }

    public RegisterResponse updateRegister(int registerId, CreateRegisterRequest request, String token) throws Exception {
        String clearToken = jwtService.clearToken(token);
        int userId = jwtService.getUserIdFromToken(clearToken);

        Register registerFound = registerRepositoryAdapter.getRegisterById(registerId);

        if (registerFound.userId() != userId) {
            throw new RegisterManipulationException("You're not responsible for this register!");
        }

        List<Register> registerWithTheSameDateAndType = registerRepositoryAdapter.getUserRegisterByDateAndTypeExcludingId(userId, request.date(), request.activityType(), registerId);

        if (!registerWithTheSameDateAndType.isEmpty()) {
            throw new RegisterManipulationException("You already have a register of this type in this date!");
        }

        Register registerToBeUpdated = registerDTOMapper.toRegisterWithId(request, userId, registerId);

        try {
            log.info("User: {} trying to edit a Register with id: {}", jwtService.getEmailFromToken(clearToken), registerToBeUpdated.id());
            Register registerUpdated = registerRepositoryAdapter.updateRegister(registerToBeUpdated);
            log.info("User: {} edited Register with id: {}", jwtService.getEmailFromToken(clearToken), registerUpdated.id());
            return registerDTOMapper.toResponse(registerUpdated);
        } catch (Exception e) {
            log.error("User: {} failed to edit a Register with id: {}, because: {}", jwtService.getEmailFromToken(clearToken), registerToBeUpdated.id(), e.toString());
            throw new Exception(e.getMessage());
        }
    }
}
