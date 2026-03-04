package com.tade.secure_file_api.exception;

/*
* Custom exception for handling incorrect password scenarios during login.
*/

public class IncorrectPasswordException extends RuntimeException {
    public IncorrectPasswordException(String message) {
        super(message);
    }
    
}
