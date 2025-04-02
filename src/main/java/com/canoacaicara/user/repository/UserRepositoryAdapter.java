package com.canoacaicara.user.repository;

import com.canoacaicara.user.User;
import com.canoacaicara.user.repository.mapper.UserEntityMapper;
import com.canoacaicara.user.repository.persistance.UserEntity;
import com.canoacaicara.user.repository.persistance.UserJpaRepository;

import java.util.List;
import java.util.Optional;

public class UserRepositoryAdapter {
    private final UserJpaRepository userJpaRepository;
    private final UserEntityMapper userEntityMapper;

    public UserRepositoryAdapter(UserJpaRepository userJpaRepository, UserEntityMapper userEntityMapper) {
        this.userJpaRepository = userJpaRepository;
        this.userEntityMapper = userEntityMapper;
    }

    public User createUser(User userDomain) {
        UserEntity userEntity = userEntityMapper.toEntity(userDomain);
        UserEntity userSaved = userJpaRepository.save(userEntity);
        return userEntityMapper.toDomain(userSaved);
    }

    public List<User> getUsers() {
        List<UserEntity> usersEntity = userJpaRepository.findAll();
        return usersEntity.stream().map(userEntityMapper::toDomain).toList();
    }

    public Optional<User> getUser(String email) {
        return userJpaRepository.findByEmail(email).map(userEntityMapper::toDomain);
    }
}
