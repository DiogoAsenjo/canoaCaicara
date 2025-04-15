package com.canoacaicara.user.service.mapper;

import com.canoacaicara.user.User;
import com.canoacaicara.user.controller.dto.CreateUserRequest;
import com.canoacaicara.user.controller.dto.UserResponse;

public class UserDTOMapper {
    public UserResponse toResponse(User user) {
        return new UserResponse(user.name(), user.email());
    }

    public User toUserDomain(CreateUserRequest request) {
        return new User(request.name(), request.email(), request.password(), request.whatsapp(), request.pix(), request.enrolledGroupsIds(), request.role());
    }
}