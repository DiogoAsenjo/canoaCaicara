package com.canoacaicara.register.application.usecases;

import com.canoacaicara.register.application.exceptions.RegisterNotFoundException;
import com.canoacaicara.register.application.mapper.RegisterDTOMapper;
import com.canoacaicara.register.domain.Register;
import com.canoacaicara.register.infrastructure.controllers.AllRegistersResponse;
import com.canoacaicara.register.infrastructure.controllers.RegisterByMonthResponse;
import com.canoacaicara.register.infrastructure.controllers.RegisterResponse;
import com.canoacaicara.register.infrastructure.gateways.RegisterGateway;
import com.canoacaicara.security.jwt.JWTService;

import java.time.LocalDate;
import java.util.List;

public class GetRegisterInteractor {
    private final RegisterGateway registerGateway;
    private final RegisterDTOMapper registerDTOMapper;
    private final JWTService jwtService;
    private final CalculatePaymentInteractor calculatePaymentInteractor;

    public GetRegisterInteractor(RegisterGateway registerGateway, RegisterDTOMapper registerDTOMapper, JWTService jwtService, CalculatePaymentInteractor calculatePaymentInteractor) {
        this.registerGateway = registerGateway;
        this.registerDTOMapper = registerDTOMapper;
        this.jwtService = jwtService;
        this.calculatePaymentInteractor = calculatePaymentInteractor;
    }

    public List<RegisterResponse> getUserRegisters(String token) {
        String clearToken = jwtService.clearToken(token);
        int userId = jwtService.getUserIdFromToken(clearToken);

        List<Register> registersFound = registerGateway.getUserRegisters(userId);

        if (registersFound.isEmpty()) {
            throw new RegisterNotFoundException("Registers not found");
        }

        return registersFound.stream().map(registerDTOMapper::toResponse).toList();
    }

    public List<AllRegistersResponse> getAllRegisters() {
        List<AllRegistersResponse> registersFound = registerGateway.getAllRegisters();

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

        List<Register> registersFound = registerGateway.getUserRegisterByDate(userId, firstDayOfMonth, lastDayOfMonth);

        if (registersFound.isEmpty()) {
            throw new RegisterNotFoundException("Registers not found");
        }

        int earnings = calculatePaymentInteractor.calculateEarnings(registersFound);

        return new RegisterByMonthResponse(earnings, registersFound.stream().map(registerDTOMapper::toResponse).toList());

    }
}
