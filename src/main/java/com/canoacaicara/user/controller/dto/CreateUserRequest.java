package com.canoacaicara.user.controller.dto;

import java.util.List;
import com.canoacaicara.common.enums.Roles;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateUserRequest(
        @NotBlank(message = "Nome é obrigatório")
        String name,
        @NotBlank(message = "Email é obrigatório")
        String email,
        @NotBlank(message = "Password é obrigatório")
        String password,
        @NotBlank(message = "Whatsapp é obrigatório")
        String whatsapp,
        @NotBlank(message = "Pix é obrigatório")
        String pix,
        List<Integer> enrolledGroupsIds, // Not mandatory
        @NotNull(message = "Role é obrigatório")
        Roles role
) {
}
