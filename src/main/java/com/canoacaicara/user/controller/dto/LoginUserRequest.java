package com.canoacaicara.user.controller.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginUserRequest(
        @NotBlank(message = "Email é obrigatório")
        @Email(message = "Insira um endereço de email válido")
        String email,
        @NotBlank(message = "Senha é obrigatória")
        String password
) {
}
