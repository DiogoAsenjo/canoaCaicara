package com.canoacaicara.user.domain;

import java.util.List;

import com.canoacaicara.common.enums.Roles;

public record User(
        Integer id,
        String name,
        String email,
        String password,
        String whatsapp,
        String pix,
        List<String> enrolledGroups,
        Roles role
) {
    public User userWithHashedPassowrd(String hashedPassword) {
        return new User(id, name, email, hashedPassword, whatsapp, pix, enrolledGroups, role);
    }

    public User(String name, String email, String password, String whatsapp, String pix, List<String> enrolledGroups, Roles role) {
        this(null, name, email, password, whatsapp, pix, enrolledGroups, role);
    }

}
