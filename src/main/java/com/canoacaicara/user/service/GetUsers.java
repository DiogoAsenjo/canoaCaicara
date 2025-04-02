package com.canoacaicara.user.service;

import com.canoacaicara.user.repository.UserRepositoryAdapter;
import com.canoacaicara.user.service.exceptions.UserNotFoundException;
import com.canoacaicara.user.service.mapper.UserDTOMapper;
import com.canoacaicara.user.controller.dto.UserResponse;
import com.canoacaicara.user.User;

import java.util.List;

public class GetUsers {
    private final UserRepositoryAdapter userRepositoryAdapter;
    private final UserDTOMapper userDTOMapper;

    public GetUsers(UserRepositoryAdapter userRepositoryAdapter, UserDTOMapper userDTOMapper) {
        this.userRepositoryAdapter = userRepositoryAdapter;
        this.userDTOMapper = userDTOMapper;
    }

    public List<UserResponse> getUsers() {
        List<User> usersDomainFound = userRepositoryAdapter.getUsers();

        if (usersDomainFound.isEmpty()) {
            throw new UserNotFoundException("Users not found");
        }

        return usersDomainFound.stream().map(userDTOMapper::toResponse).toList();
    }
}
