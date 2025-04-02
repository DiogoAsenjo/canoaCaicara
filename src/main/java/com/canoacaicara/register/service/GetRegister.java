package com.canoacaicara.register.service;

import com.canoacaicara.register.repository.RegisterRepositoryAdapter;
import com.canoacaicara.register.service.exceptions.RegisterNotFoundException;
import com.canoacaicara.register.service.mapper.RegisterDTOMapper;
import com.canoacaicara.register.Register;
import com.canoacaicara.register.controller.dto.AllRegistersResponse;
import com.canoacaicara.register.controller.dto.RegisterByMonthResponse;
import com.canoacaicara.register.controller.dto.RegisterResponse;
import com.canoacaicara.security.jwt.JWTService;

import java.time.LocalDate;
import java.util.List;

public class GetRegister {
    private final RegisterRepositoryAdapter registerRepositoryAdapter;
    private final RegisterDTOMapper registerDTOMapper;
    private final JWTService jwtService;
    private final CalculatePayment calculatePayment;

    public GetRegister(RegisterRepositoryAdapter registerRepositoryAdapter, RegisterDTOMapper registerDTOMapper, JWTService jwtService, CalculatePayment calculatePayment) {
        this.registerRepositoryAdapter = registerRepositoryAdapter;
        this.registerDTOMapper = registerDTOMapper;
        this.jwtService = jwtService;
        this.calculatePayment = calculatePayment;
    }

    public List<RegisterResponse> getUserRegisters(String token) {
        String clearToken = jwtService.clearToken(token);
        int userId = jwtService.getUserIdFromToken(clearToken);

        List<Register> registersFound = registerRepositoryAdapter.getUserRegisters(userId);

        if (registersFound.isEmpty()) {
            throw new RegisterNotFoundException("Registers not found");
        }

        return registersFound.stream().map(registerDTOMapper::toResponse).toList();
    }

    public List<AllRegistersResponse> getAllRegisters() {
        List<AllRegistersResponse> registersFound = registerRepositoryAdapter.getAllRegisters();

        if (registersFound.isEmpty()) {
            throw new RegisterNotFoundException("Registers not found");
        }

        return registersFound;
    }

    public RegisterByMonthResponse getUserRegistersByMonth(String token, LocalDate date) {
        String clearToken = jwtService.clearToken(token);
        int userId = jwtService.getUserIdFromToken(clearToken);

        LocalDate firstDayOfMonth = date.withDayOfMonth(1);
        LocalDate lastDayOfMonth = date.withDayOfMonth(date.lengthOfMonth());

        List<Register> registersFound = registerRepositoryAdapter.getUserRegisterByDate(userId, firstDayOfMonth, lastDayOfMonth);

        if (registersFound.isEmpty()) {
            throw new RegisterNotFoundException("Registers not found");
        }

        int earnings = calculatePayment.calculateEarnings(registersFound);

        return new RegisterByMonthResponse(earnings, registersFound.stream().map(registerDTOMapper::toResponse).toList());

    }
}
