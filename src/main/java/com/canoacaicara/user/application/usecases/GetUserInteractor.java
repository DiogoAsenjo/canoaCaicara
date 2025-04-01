package com.canoacaicara.user.application.usecases;

import com.canoacaicara.user.application.exceptions.UserNotFoundException;
import com.canoacaicara.user.application.mapper.UserDTOMapper;
import com.canoacaicara.user.infrastructure.controllers.UserResponse;
import com.canoacaicara.user.infrastructure.gateways.UserGateway;
import com.canoacaicara.user.domain.User;

public class GetUserInteractor {
    private final UserGateway userGateway;
    private final UserDTOMapper userDTOMapper;

    public GetUserInteractor(UserGateway userGateway, UserDTOMapper userDTOMapper) {
        this.userGateway = userGateway;
        this.userDTOMapper = userDTOMapper;
    }

    public UserResponse getUser(String email) {
        User userDomainFound = userGateway.getUser(email).orElseThrow(()-> new UserNotFoundException("User not found"));
        return userDTOMapper.toResponse(userDomainFound);
    }
}
