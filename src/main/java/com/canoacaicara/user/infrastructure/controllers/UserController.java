package com.canoacaicara.user.infrastructure.controllers;

import com.canoacaicara.common.ApiReponse;
import com.canoacaicara.security.jwt.TokenJWT;
import com.canoacaicara.user.application.usecases.CreateUserInteractor;
import com.canoacaicara.user.application.usecases.GetUserInteractor;
import com.canoacaicara.user.application.usecases.GetUsersInteractor;
import com.canoacaicara.user.application.usecases.LoginUserInteractor;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("users")
public class UserController {
    private final CreateUserInteractor createUserInteractor;
    private final GetUsersInteractor getUsersInteractor;
    private final GetUserInteractor getUserInteractor;
    private final LoginUserInteractor loginUserInteractor;

    public UserController(CreateUserInteractor createUserInteractor, GetUsersInteractor getUsersInteractor, GetUserInteractor getUserInteractor, LoginUserInteractor loginUserInteractor) {
        this.createUserInteractor = createUserInteractor;
        this.getUsersInteractor = getUsersInteractor;
        this.getUserInteractor = getUserInteractor;
        this.loginUserInteractor = loginUserInteractor;
    }

    @PostMapping()
    ResponseEntity<ApiReponse<UserResponse>> createUser(@Valid @RequestBody CreateUserRequest request) throws Exception {
        ApiReponse<UserResponse> response = new ApiReponse<>("User created successfully!", createUserInteractor.createUser(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/")
    ResponseEntity<ApiReponse<UserResponse>> getUser(@RequestParam String email) {
        ApiReponse<UserResponse> response = new ApiReponse<>("User found!", getUserInteractor.getUser(email));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping
    ResponseEntity<ApiReponse<List<UserResponse>>> getUsers() {
        ApiReponse<List<UserResponse>> response = new ApiReponse<>("Users found!", getUsersInteractor.getUsers());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/login")
    ResponseEntity<ApiReponse<TokenJWT>> login(@Valid @RequestBody LoginUserRequest loginUserRequest) {
        ApiReponse<TokenJWT> response = new ApiReponse<>("Logged in successfully!", loginUserInteractor.login(loginUserRequest.email(), loginUserRequest.password()));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    //Route to test permission only.
    @GetMapping("/admin")
    ResponseEntity<ApiReponse<String>> adminOnly() {
        ApiReponse<String> response = new ApiReponse<>("You're an admin!", null);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
