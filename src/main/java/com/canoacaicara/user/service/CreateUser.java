package com.canoacaicara.user.service;

import com.canoacaicara.user.repository.UserRepositoryAdapter;
import com.canoacaicara.user.service.exceptions.CreateUserException;
import com.canoacaicara.user.service.mapper.UserDTOMapper;
import com.canoacaicara.user.controller.dto.CreateUserRequest;
import com.canoacaicara.user.controller.dto.UserResponse;
import com.canoacaicara.common.enums.Roles;
import com.canoacaicara.groups.persistance.GroupEntity;
import com.canoacaicara.user.User;
import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.Set;

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
        
        // DEV: TESTAR
        if (userDomain.role() == Roles.ALUNO){
            userDomain = linkUserToGroupObjects(userDomain); 
        }

        String hashedPassword = passwordEncoder.encode(userDomain.password());
        User userWithHashedPassword = userDomain.userWithHashedPassowrd(hashedPassword);

        try {
            log.debug("Trying to create a new User: {}", userDomain.email());
            
            User userCreated = userRepositoryAdapter.createUser(userWithHashedPassword);

            log.debug("User: {} created with id: {}", userCreated.email(), userCreated.id());

            return userDTOMapper.toResponse(userCreated);
        } catch (Exception e) {
            log.error("Error trying to create a new User, {}", e.toString());

            // DESENVOLVER ROLLBACK CASO USUÁRIO TENHA SIDO CRIADO, MAS NÃO MATRICULOU NAS TURMAS

            throw new CreateUserException(e.getMessage());
        }
    }

    private User linkUserToGroupObjects(User user){

        // DEV: VALIDAR SE IDs DOS GRUPOS VIERAM CERTO

        Set<GroupEntity> groups = new HashSet<>();

        for(Integer enrolledGroupId : user.enrolledGroupsIds()){

            GroupEntity groupEntity = new GroupEntity();
            groupEntity.setId(enrolledGroupId);

            groups.add(groupEntity);
        }

        return new User(user.name(), user.email(), user.password(), user.whatsapp(), user.pix(), user.enrolledGroupObjects(), user.role());
    }
}
