package com.canoacaicara.register.service.exceptions;

public class RegisterNotFoundException extends  RuntimeException{
    public RegisterNotFoundException(String message) {
        super(message);
    }

    public RegisterNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public RegisterNotFoundException(Throwable cause) {
        super(cause);
    }
}
