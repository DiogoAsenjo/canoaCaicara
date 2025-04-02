package com.canoacaicara.register.service.mapper;

import com.canoacaicara.register.Register;
import com.canoacaicara.register.controller.dto.AllRegistersResponse;
import com.canoacaicara.register.controller.dto.CreateRegisterRequest;
import com.canoacaicara.register.controller.dto.RegisterResponse;
import com.canoacaicara.register.repository.persistance.RegisterEntity;

public class RegisterDTOMapper {
    public RegisterResponse toResponse(Register register) {
        return new RegisterResponse(register.id(), register.date(), register.quantity(), register.activityType());
    }

    public Register toRegister(CreateRegisterRequest request, int userId) {
        return new Register(userId, request.activityType(),request.date(), request.quantity());
    }

    public Register toRegisterWithId(CreateRegisterRequest request, int userId, int registerId) {
        return new Register(registerId, userId, request.activityType(),request.date(), request.quantity());
    }

    public AllRegistersResponse toAllRegistersResponse(RegisterEntity register) {
        return new AllRegistersResponse(register.getId(), register.getDate(), register.getQuantity(), register.getActivityType(), register.getUser().getName());
    }
}
