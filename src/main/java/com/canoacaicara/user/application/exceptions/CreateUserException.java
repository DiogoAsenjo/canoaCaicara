package com.canoacaicara.user.application.exceptions;

public class CreateUserException extends RuntimeException {
    public CreateUserException(String message) {
        super(message);
    }

    public CreateUserException(String message, Throwable cause) {
        super(message, cause);
    }

    public CreateUserException(Throwable cause) {
        super(cause);
    }
}
