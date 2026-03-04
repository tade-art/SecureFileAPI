package com.tade.secure_file_api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tade.secure_file_api.model.User;
import com.tade.secure_file_api.service.UserService;
import lombok.RequiredArgsConstructor;

/*
* Controller for handling authentication-related endpoints (e.g., registration)
*/
@RestController
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    // Endpoint for user registration - contacts UserService to create a new user
    @PostMapping("/register")
    public ResponseEntity register(@RequestParam String email, @RequestParam String password){
        
        //todo: do not return full user obj
        User user = userService.registerUser(email, password);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
}
