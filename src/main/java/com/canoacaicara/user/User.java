package com.canoacaicara.user;

import java.util.List;
import java.util.Set;

import com.canoacaicara.common.enums.Roles;
import com.canoacaicara.groups.persistance.GroupEntity;


public record User(
        Integer id,
        String name,
        String email,
        String password,
        String whatsapp,
        String pix,
        List<Integer> enrolledGroupsIds,
        Set<GroupEntity> enrolledGroupObjects,
        Roles role
) {
    public User userWithHashedPassowrd(String hashedPassword) {
        return new User(id, name, email, hashedPassword, whatsapp, pix, enrolledGroupsIds, enrolledGroupObjects, role);
    }

    public User(String name, String email, String password, String whatsapp, String pix, List<Integer> enrolledGroupsIds, Roles role){
        this(null, name, email, password, whatsapp, pix, enrolledGroupsIds, null, role);
    }
    
    public User(String name, String email, String password, String whatsapp, String pix, Set<GroupEntity> enrolledGroupObjects, Roles role) {
        this(null, name, email, password, whatsapp, pix, null, enrolledGroupObjects, role);
    }
}
