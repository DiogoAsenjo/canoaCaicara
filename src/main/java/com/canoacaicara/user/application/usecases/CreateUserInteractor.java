package com.canoacaicara.user.application.usecases;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.canoacaicara.common.enums.Roles;
import com.canoacaicara.groups.usecases.EnrollUserInteractor;
import com.canoacaicara.user.application.exceptions.CreateUserException;
import com.canoacaicara.user.application.mapper.UserDTOMapper;
import com.canoacaicara.user.domain.User;
import com.canoacaicara.user.infrastructure.controllers.CreateUserRequest;
import com.canoacaicara.user.infrastructure.controllers.UserResponse;
import com.canoacaicara.user.infrastructure.gateways.UserGateway;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CreateUserInteractor {
    private final UserGateway userGateway;
    private final PasswordEncoder passwordEncoder;
    private final EnrollUserInteractor enrollUserInterector;
    private final UserDTOMapper userDTOMapper;

    public CreateUserInteractor(UserGateway userGateway, PasswordEncoder passwordEncoder, EnrollUserInteractor enrollUserInterector, UserDTOMapper userDTOMapper) {
        this.userGateway = userGateway;
        this.passwordEncoder = passwordEncoder;
        this.enrollUserInterector = enrollUserInterector;
        this.userDTOMapper = userDTOMapper;
    }

    public UserResponse createUser(CreateUserRequest request) throws Exception {
        User userDomain = userDTOMapper.toUser(request);
        String hashedPassword = passwordEncoder.encode(userDomain.password());
        User userWithHashedPassword = userDomain.userWithHashedPassowrd(hashedPassword);

        try {
            log.debug("Trying to create a new User: {}", userDomain.email());
            User userCreated = userGateway.createUser(userWithHashedPassword);
            log.debug("User: {} created with id: {}", userCreated.email(), userCreated.id());

            // DESENVOLVER MATRÍCULA DO ALUNO NAS TURMAS
            if(userDomain.role() == Roles.ALUNO){
                enrollUserInterector.enrollUser(userCreated.id(), userDomain.enrolledGroups());
    
            }

            return userDTOMapper.toResponse(userCreated);
        } catch (Exception e) {
            log.error("Error trying to create a new User, {}", e.toString());

            // DESENVOLVER ROLLBACK CASO USUÁRIO TENHA SIDO CRIADO, MAS NÃO MATRICULOU NAS TURMAS

            throw new CreateUserException(e.getMessage());
        }
    }
}
