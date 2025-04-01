package com.canoacaicara.user.infrastructure.controllers;

import com.canoacaicara.common.enums.Roles;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateUserRequest(
        @NotBlank(message = "Name is mandatory")
        String name,
        @NotBlank(message = "Email is mandatory")
        String email,
        @NotBlank(message = "Password is mandatory")
        String password,
        @NotBlank(message = "Whatsapp is mandatory")
        String whatsapp,
        @NotBlank(message = "Pix is mandatory")
        String pix,
        @NotNull(message = "Role is mandatory")
        Roles role
) {
}
