package com.canoacaicara.common;

import com.canoacaicara.register.application.exceptions.RegisterManipulationException;
import com.canoacaicara.register.application.exceptions.RegisterNotFoundException;
import com.canoacaicara.user.application.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;


@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ApiReponse> handleException(Exception e) {
        ApiReponse response = new ApiReponse<>(e.getMessage(), null);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiReponse> handleUserNotFoundException(Exception e) {
        ApiReponse response = new ApiReponse<>(e.getMessage(), null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(RegisterNotFoundException.class)
    public ResponseEntity<ApiReponse> handleRegisterNotFoundException(Exception e) {
        ApiReponse response = new ApiReponse<>(e.getMessage(), null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(RegisterManipulationException.class)
    public ResponseEntity<ApiReponse> RegisterCreationException(Exception e) {
        ApiReponse response = new ApiReponse<>(e.getMessage(), null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiReponse> handleNotValidException(MethodArgumentNotValidException e) {
        List<String> errors = new ArrayList<>();

        for (FieldError error : e.getBindingResult().getFieldErrors()) {
            errors.add(error.getDefaultMessage());
        }

        ApiReponse response = new ApiReponse(errors, null);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
