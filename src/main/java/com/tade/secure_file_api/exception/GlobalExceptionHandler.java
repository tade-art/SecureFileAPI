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

    // Method to check if user does not exist and throw custom exception
    @ExceptionHandler(EmailNotFoundException.class)
    public ResponseEntity handleEmailNotFoundException(EmailNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    //Method to check if password is incorrect and throw custom exception
    @ExceptionHandler(IncorrectPasswordException.class)
    public ResponseEntity handleIncorrectPasswordException(IncorrectPasswordException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
    }

    ///Method to return an exception if a token is wrong or expired
    @ExceptionHandler(BadTokenException.class)
    public ResponseEntity handleBadTokenException(BadTokenException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
    }
    
}
