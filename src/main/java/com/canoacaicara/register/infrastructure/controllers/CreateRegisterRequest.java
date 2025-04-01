package com.canoacaicara.register.infrastructure.controllers;

import com.canoacaicara.common.enums.ActivityType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public record CreateRegisterRequest(
        @NotNull(message = "Date is mandatoy")
        LocalDate date,

        @NotNull(message = "Quantity is mandatory")
        @Positive(message = "Quantity should be positive")
        int quantity,

        @NotNull(message = "Activity type is mandatory")
        ActivityType activityType
) {
}
