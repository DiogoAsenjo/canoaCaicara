package com.canoacaicara.user;

import com.canoacaicara.common.enums.Roles;

public record User(
        Integer id,
        String name,
        String email,
        String password,
        String whatsapp,
        String pix,
        Roles role
) {
    public User userWithHashedPassowrd(String hashedPassword) {
        return new User(id, name, email, hashedPassword,whatsapp, pix, role);
    }

    public User(String name, String email, String password, String whatsapp, String pix, Roles role) {
        this(null, name, email, password, whatsapp, pix, role);
    }

}
