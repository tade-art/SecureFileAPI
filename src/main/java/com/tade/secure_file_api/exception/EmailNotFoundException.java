package com.tade.secure_file_api.exception;

/*
* Custom exception to indicate that an email is not found in the system.
*/
public class EmailNotFoundException extends RuntimeException {
    public EmailNotFoundException(String message) {
        super(message);
    }
    
}
