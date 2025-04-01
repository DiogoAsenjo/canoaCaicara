package com.canoacaicara.register.infrastructure.controllers;

import java.util.List;

public record RegisterByMonthResponse(
        int earnings,
        List<RegisterResponse> registers
) {
}
