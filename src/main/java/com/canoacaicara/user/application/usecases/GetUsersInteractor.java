package com.canoacaicara.user.application.usecases;

import com.canoacaicara.user.application.exceptions.UserNotFoundException;
import com.canoacaicara.user.application.mapper.UserDTOMapper;
import com.canoacaicara.user.infrastructure.controllers.UserResponse;
import com.canoacaicara.user.infrastructure.gateways.UserGateway;
import com.canoacaicara.user.domain.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

public class GetUsersInteractor {
    private final UserGateway userGateway;
    private final UserDTOMapper userDTOMapper;

    public GetUsersInteractor(UserGateway userGateway, UserDTOMapper userDTOMapper) {
        this.userGateway = userGateway;
        this.userDTOMapper = userDTOMapper;
    }

    public List<UserResponse> getUsers() {
        List<User> usersDomainFound = userGateway.getUsers();

        if (usersDomainFound.isEmpty()) {
            throw new UserNotFoundException("Users not found");
        }

        return usersDomainFound.stream().map(userDTOMapper::toResponse).toList();
    }
}
