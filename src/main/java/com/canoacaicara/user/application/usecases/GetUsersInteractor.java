package com.canoacaicara.user.application.usecases;

import java.util.List;

import com.canoacaicara.user.application.exceptions.UserNotFoundException;
import com.canoacaicara.user.application.mapper.UserDTOMapper;
import com.canoacaicara.user.domain.User;
import com.canoacaicara.user.infrastructure.controllers.UserResponse;
import com.canoacaicara.user.infrastructure.gateways.UserGateway;

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
            throw new UserNotFoundException("Nenhum usuário encontrado!");
        }

        return usersDomainFound.stream().map(userDTOMapper::toResponse).toList();
    }
}
