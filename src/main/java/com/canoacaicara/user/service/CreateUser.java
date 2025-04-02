package com.canoacaicara.user.service;

import com.canoacaicara.user.repository.UserRepositoryAdapter;
import com.canoacaicara.user.service.exceptions.CreateUserException;
import com.canoacaicara.user.service.mapper.UserDTOMapper;
import com.canoacaicara.user.controller.dto.CreateUserRequest;
import com.canoacaicara.user.controller.dto.UserResponse;
import com.canoacaicara.user.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;

@Slf4j
public class CreateUser {
    private final PasswordEncoder passwordEncoder;
    private final UserDTOMapper userDTOMapper;
    private final UserRepositoryAdapter userRepositoryAdapter;

    public CreateUser(PasswordEncoder passwordEncoder, UserDTOMapper userDTOMapper, UserRepositoryAdapter userRepositoryAdapter) {
        this.passwordEncoder = passwordEncoder;
        this.userDTOMapper = userDTOMapper;
        this.userRepositoryAdapter = userRepositoryAdapter;
    }

    public UserResponse createUser(CreateUserRequest request) throws Exception {
        User userDomain = userDTOMapper.toUserDomain(request);
        String hashedPassword = passwordEncoder.encode(userDomain.password());
        User userWithHashedPassword = userDomain.userWithHashedPassowrd(hashedPassword);

        try {
            log.debug("Trying to create a new User: {}", userDomain.email());
            User userCreated = userRepositoryAdapter.createUser(userWithHashedPassword);
            log.debug("User: {} created with id: {}", userCreated.email(), userCreated.id());
            return userDTOMapper.toResponse(userCreated);
        } catch (Exception e) {
            log.error("Error trying to create a new User, {}", e.toString());
            throw new CreateUserException(e.getMessage());
        }
    }
}
