package com.canoacaicara.user.service;

import com.canoacaicara.user.repository.UserRepositoryAdapter;
import com.canoacaicara.user.service.exceptions.UserNotFoundException;
import com.canoacaicara.user.service.mapper.UserDTOMapper;
import com.canoacaicara.user.controller.dto.UserResponse;
import com.canoacaicara.user.User;

public class GetUser {
    private final UserRepositoryAdapter userRepositoryAdapter;
    private final UserDTOMapper userDTOMapper;

    public GetUser(UserRepositoryAdapter userRepositoryAdapter, UserDTOMapper userDTOMapper) {
        this.userRepositoryAdapter = userRepositoryAdapter;
        this.userDTOMapper = userDTOMapper;
    }

    public UserResponse getUser(String email) {
        User userDomainFound = userRepositoryAdapter.getUser(email).orElseThrow(()-> new UserNotFoundException("User not found"));
        return userDTOMapper.toResponse(userDomainFound);
    }
}
