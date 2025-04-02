package com.canoacaicara.register.controller.dto;

import com.canoacaicara.common.enums.ActivityType;

import java.time.LocalDate;

public record AllRegistersResponse(
        int id,
        LocalDate date,
        int quantity,
        ActivityType activityType,
        String responsible
) {
}
