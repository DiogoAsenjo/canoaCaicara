package com.canoacaicara.register.service;

import com.canoacaicara.register.repository.RegisterRepositoryAdapter;
import com.canoacaicara.register.service.exceptions.RegisterManipulationException;
import com.canoacaicara.register.service.mapper.RegisterDTOMapper;
import com.canoacaicara.register.Register;
import com.canoacaicara.security.jwt.JWTService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DeleteRegisterInteractor {
    private final RegisterRepositoryAdapter registerRepositoryAdapter;
    private final RegisterDTOMapper registerDTOMapper;
    private final JWTService jwtService;

    public DeleteRegisterInteractor(RegisterRepositoryAdapter registerRepositoryAdapter, RegisterDTOMapper registerDTOMapper, JWTService jwtService) {
        this.registerRepositoryAdapter = registerRepositoryAdapter;
        this.registerDTOMapper = registerDTOMapper;
        this.jwtService = jwtService;
    }

    public void deleteRegister(int registerId, String token) throws Exception {
        String clearToken = jwtService.clearToken(token);
        int userId = jwtService.getUserIdFromToken(clearToken);

        Register registerFound = registerRepositoryAdapter.getRegisterById(registerId);

        if (registerFound.userId() != userId) {
            throw new RegisterManipulationException("You're not responsible for this register!");
        }

        try {
            log.info("User: {} trying to delete a Register with id: {}", jwtService.getEmailFromToken(clearToken), registerFound.id());
            registerRepositoryAdapter.deleteRegister(registerId);
            log.info("User: {} deleted a Register with id: {}", jwtService.getEmailFromToken(clearToken), registerFound.id());
        } catch (Exception e) {
            log.error("User: {} failed to delete a Register with id: {}, because: {}", jwtService.getEmailFromToken(clearToken), registerFound.id(), e.toString());
            throw new Exception(e.getMessage());
        }
    }
}
