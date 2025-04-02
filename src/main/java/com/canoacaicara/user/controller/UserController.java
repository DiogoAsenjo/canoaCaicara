package com.canoacaicara.user.controller;

import com.canoacaicara.common.ApiReponse;
import com.canoacaicara.user.controller.dto.CreateUserRequest;
import com.canoacaicara.user.controller.dto.LoginUserRequest;
import com.canoacaicara.user.controller.dto.UserResponse;
import com.canoacaicara.security.jwt.TokenJWT;
import com.canoacaicara.user.service.CreateUser;
import com.canoacaicara.user.service.GetUser;
import com.canoacaicara.user.service.GetUsers;
import com.canoacaicara.user.service.Login;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("users")
public class UserController {
    private final CreateUser createUser;
    private final GetUsers getUsers;
    private final GetUser getUser;
    private final Login login;

    public UserController(CreateUser createUser, GetUsers getUsers, GetUser getUser, Login login) {
        this.createUser = createUser;
        this.getUsers = getUsers;
        this.getUser = getUser;
        this.login = login;
    }

    @PostMapping()
    ResponseEntity<ApiReponse<UserResponse>> createUser(@Valid @RequestBody CreateUserRequest request) throws Exception {
        ApiReponse<UserResponse> response = new ApiReponse<>("User created successfully!", createUser.createUser(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/")
    ResponseEntity<ApiReponse<UserResponse>> getUser(@RequestParam String email) {
        ApiReponse<UserResponse> response = new ApiReponse<>("User found!", getUser.getUser(email));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping
    ResponseEntity<ApiReponse<List<UserResponse>>> getUsers() {
        ApiReponse<List<UserResponse>> response = new ApiReponse<>("Users found!", getUsers.getUsers());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/login")
    ResponseEntity<ApiReponse<TokenJWT>> login(@Valid @RequestBody LoginUserRequest loginUserRequest) {
        ApiReponse<TokenJWT> response = new ApiReponse<>("Logged in successfully!", login.login(loginUserRequest.email(), loginUserRequest.password()));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    //Route to test permission only.
    @GetMapping("/admin")
    ResponseEntity<ApiReponse<String>> adminOnly() {
        ApiReponse<String> response = new ApiReponse<>("You're an admin!", null);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
