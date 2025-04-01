package com.canoacaicara.user.infrastructure.controllers;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginUserRequest(
        @NotBlank(message = "Email is mandatory")
        @Email(message = "Insert a valid email")
        String email,
        @NotBlank(message = "Password is mandatory")
        String password
) {
}
