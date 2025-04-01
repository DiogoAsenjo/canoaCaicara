package com.canoacaicara.register.application.usecases;

import com.canoacaicara.register.domain.Register;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

public class CalculatePaymentInteractor {
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
        };
    }
}
