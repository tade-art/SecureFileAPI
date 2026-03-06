package com.tade.secure_file_api.exception;

/*
* Custom exception to indicate that an email is not found in the system.
*/
public class BadTokenException extends RuntimeException {
    public BadTokenException(String message) {
        super(message);
    }
    
}
