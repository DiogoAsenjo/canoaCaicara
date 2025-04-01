package com.canoacaicara.user.infrastructure.gateways;

import com.canoacaicara.user.domain.User;
import com.canoacaicara.user.infrastructure.persistance.UserEntity;

public class UserEntityMapper {
    UserEntity toEntity(User userDomain) {
        return new UserEntity(userDomain.name(), userDomain.email(), userDomain.password(), userDomain.whatsapp(), userDomain.pix(), userDomain.role());
    }

    User toDomain(UserEntity userEntity) {
        return new User(userEntity.getId(), userEntity.getName(), userEntity.getEmail(), userEntity.getPassword(), userEntity.getWhatsapp(), userEntity.getPix(), userEntity.getRole());
    }


}
