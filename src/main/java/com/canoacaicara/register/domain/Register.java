package com.canoacaicara.register.domain;

import com.canoacaicara.common.enums.ActivityType;

import java.time.LocalDate;

public record Register(
        Integer id,
        int userId,
        ActivityType activityType,
        LocalDate date,
        int quantity
) {
    public Register(int userId, ActivityType activityType, LocalDate date, int quantity) {
        this(null, userId, activityType, date, quantity);
    }
}
