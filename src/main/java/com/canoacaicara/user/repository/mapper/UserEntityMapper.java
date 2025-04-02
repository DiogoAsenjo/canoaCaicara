package com.canoacaicara.user.repository.mapper;

import com.canoacaicara.user.User;
import com.canoacaicara.user.repository.persistance.UserEntity;

public class UserEntityMapper {
    public UserEntity toEntity(User userDomain) {
        return new UserEntity(userDomain.name(), userDomain.email(), userDomain.password(), userDomain.whatsapp(), userDomain.pix(), userDomain.role());
    }

    public User toDomain(UserEntity userEntity) {
        return new User(userEntity.getId(), userEntity.getName(), userEntity.getEmail(), userEntity.getPassword(), userEntity.getWhatsapp(), userEntity.getPix(), userEntity.getRole());
    }


}
