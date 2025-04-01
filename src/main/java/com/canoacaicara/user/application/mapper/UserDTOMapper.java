package com.canoacaicara.user.application.mapper;

import com.canoacaicara.user.domain.User;
import com.canoacaicara.user.infrastructure.controllers.CreateUserRequest;
import com.canoacaicara.user.infrastructure.controllers.UserResponse;

public class UserDTOMapper {
    public UserResponse toResponse(User user) {
        return new UserResponse(user.name(), user.email());
    }

    public User toUser(CreateUserRequest request) {
        return new User(request.name(), request.email(), request.password(), request.whatsapp(), request.pix(), request.role());
    }
}