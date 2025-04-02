package com.canoacaicara.register.controller.dto;

import java.util.List;

public record RegisterByMonthResponse(
        int earnings,
        List<RegisterResponse> registers
) {
}
