package com.canoacaicara.register.service.exceptions;

public class RegisterManipulationException extends RuntimeException{
    public RegisterManipulationException(String message) {
        super(message);
    }

    public RegisterManipulationException(String message, Throwable cause) {
        super(message, cause);
    }

    public RegisterManipulationException(Throwable cause) {
        super(cause);
    }
}

