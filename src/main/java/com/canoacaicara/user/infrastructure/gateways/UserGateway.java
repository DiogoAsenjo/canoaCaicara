package com.canoacaicara.user.infrastructure.gateways;

import com.canoacaicara.user.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserGateway {
    User createUser(User user);
    List<User> getUsers();
    Optional<User> getUser(String email);
}
