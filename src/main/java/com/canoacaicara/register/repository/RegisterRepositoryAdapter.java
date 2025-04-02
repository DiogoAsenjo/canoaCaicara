package com.canoacaicara.register.repository;

import com.canoacaicara.common.enums.ActivityType;
import com.canoacaicara.register.repository.mapper.RegisterEntityMapper;
import com.canoacaicara.register.repository.persistance.RegisterEntity;
import com.canoacaicara.register.repository.persistance.RegisterJpaRepository;
import com.canoacaicara.register.service.exceptions.RegisterNotFoundException;
import com.canoacaicara.register.service.mapper.RegisterDTOMapper;
import com.canoacaicara.register.Register;
import com.canoacaicara.register.controller.dto.AllRegistersResponse;

import java.time.LocalDate;
import java.util.List;

public class RegisterRepositoryAdapter {
    private final RegisterJpaRepository registerJpaRepository;
    private final RegisterEntityMapper registerEntityMapper;
    private final RegisterDTOMapper registerDTOMapper;

    public RegisterRepositoryAdapter(RegisterJpaRepository registerJpaRepository, RegisterEntityMapper registerEntityMapper, RegisterDTOMapper registerDTOMapper) {
        this.registerJpaRepository = registerJpaRepository;
        this.registerEntityMapper = registerEntityMapper;
        this.registerDTOMapper = registerDTOMapper;
    }

    public Register createRegister(Register register) {
        RegisterEntity registerEntity = registerEntityMapper.toEntity(register);
        RegisterEntity registerEntitySaved = registerJpaRepository.save(registerEntity);
        return registerEntityMapper.toDomain(registerEntitySaved);
    }

    public List<Register> getUserRegisters(int userId) {
        List<RegisterEntity> registers = registerJpaRepository.findByUserId(userId);
        return registers.stream().map(registerEntityMapper::toDomain).toList();
    }

    public List<AllRegistersResponse> getAllRegisters() {
        List<RegisterEntity> registers = registerJpaRepository.findAll();
        return registers.stream().map(registerDTOMapper::toAllRegistersResponse).toList();
    }

    public List<Register> getUserRegisterByDateAndType(int userId, LocalDate date, ActivityType activityType) {
        List<RegisterEntity> register = registerJpaRepository.findByUserIdAndDateAndActivityType(userId, date, activityType);
        return register.stream().map(registerEntityMapper::toDomain).toList();
    }

    public List<Register> getUserRegisterByDateAndTypeExcludingId(int userId, LocalDate date, ActivityType activityType, int registerId) {
        List<RegisterEntity> register = registerJpaRepository.findByUserIdAndDateAndActivityTypeAndIdNot(userId, date, activityType, registerId);
        return register.stream().map(registerEntityMapper::toDomain).toList();
    }

    public List<Register> getUserRegisterByDate(int userId, LocalDate startOfMonth, LocalDate endOfMonth) {
        List<RegisterEntity> register = registerJpaRepository.findByUserIdAndDateBetween(userId, startOfMonth, endOfMonth);
        return register.stream().map(registerEntityMapper::toDomain).toList();
    }

    public Register getRegisterById(int registerId) {
        RegisterEntity register = registerJpaRepository.findById(registerId).orElseThrow(() -> new RegisterNotFoundException("Register not found"));
        return registerEntityMapper.toDomain(register);
    }

    public Register updateRegister(Register register) {
        RegisterEntity registerEntity = registerEntityMapper.toEntity(register);
        RegisterEntity registerSaved = registerJpaRepository.save(registerEntity);
        return registerEntityMapper.toDomain(registerSaved);
    }

    public void deleteRegister(int registerId) {
        registerJpaRepository.deleteById(registerId);
    }
}
