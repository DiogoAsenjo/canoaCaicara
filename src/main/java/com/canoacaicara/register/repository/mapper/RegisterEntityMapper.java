package com.canoacaicara.register.repository.mapper;

import com.canoacaicara.register.Register;
import com.canoacaicara.register.repository.persistance.RegisterEntity;
import com.canoacaicara.user.repository.persistance.UserEntity;
import com.canoacaicara.user.repository.persistance.UserJpaRepository;
import com.canoacaicara.user.service.exceptions.UserNotFoundException;

public class RegisterEntityMapper {
  private final UserJpaRepository userJpaRepository;

    public RegisterEntityMapper(UserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }

    public RegisterEntity toEntity(Register registerDomain) {
        UserEntity user = userJpaRepository.findById(registerDomain.userId()).orElseThrow(() -> new UserNotFoundException("User not found"));
        if (registerDomain.id() == null) {
            return new RegisterEntity(user, registerDomain.date(), registerDomain.quantity(), registerDomain.activityType());
        }
        return new RegisterEntity(registerDomain.id(), user, registerDomain.date(), registerDomain.quantity(), registerDomain.activityType());
    }

    public Register toDomain(RegisterEntity registerEntity) {
        RegisterEntity register = registerEntity;
        return new Register(registerEntity.getId(), registerEntity.getUser().getId(), registerEntity.getActivityType(), registerEntity.getDate(), registerEntity.getQuantity());
    }
}
