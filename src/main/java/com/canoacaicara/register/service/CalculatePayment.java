package com.canoacaicara.register.service;

import com.canoacaicara.register.Register;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

public class CalculatePayment {
    @Value("${com.canoacaicara.aula}")
    public int aulaValue;

    @Value("${com.canoacaicara.passeio}")
    public int passeioValue;

    @Value("${com.canoacaicara.corporativo}")
    public int corporativoValue;

    public int calculateEarnings(List<Register> registers) {
        return registers.stream()
                .mapToInt(this::calculateSingleRegisterPayment)
                .sum();
    }

    public int calculateSingleRegisterPayment(Register register) {
        return switch (register.activityType()) {
            case AULA -> register.quantity() * aulaValue;
            case PASSEIO -> register.quantity() * passeioValue;
            case COORPORATIVO -> register.quantity() * corporativoValue;
            case TREINO -> register.quantity() * aulaValue; // Treino possui mesmo valor da aula. Ajustar se necessário
        };
    }
}
