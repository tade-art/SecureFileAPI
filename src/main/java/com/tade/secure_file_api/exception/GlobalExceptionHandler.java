package com.tade.secure_file_api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/*
* Global exception handler for managing custom exceptions across the application.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    //Method to check if email already exists and throw custom exception
    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity handleEmailAlreadyExistsException(EmailAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }
    
}
