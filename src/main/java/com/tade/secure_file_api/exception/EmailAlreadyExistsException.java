package com.tade.secure_file_api.exception;

/*
* Custom exception to indicate that an email address is already registered in the system.
*/
public class EmailAlreadyExistsException extends RuntimeException {
    public EmailAlreadyExistsException(String message) {
        super(message);
    }
    
}
