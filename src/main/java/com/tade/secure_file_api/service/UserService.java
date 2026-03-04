package com.tade.secure_file_api.service;

import java.time.LocalDate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tade.secure_file_api.exception.EmailAlreadyExistsException;
import com.tade.secure_file_api.model.User;
import com.tade.secure_file_api.repository.UserRepository;
import lombok.RequiredArgsConstructor;

/* 
* Service class for handling user-related operations, such as registration.
* Contains business logic for user management and interacts with UserRepository for database operations.
 */
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // Method to register a new user - checks if email already exists, encodes password, and saves user to DB
    public User registerUser(String email, String password){
        if(userRepository.existsByEmail(email)){
            throw new EmailAlreadyExistsException("Email already registered");
        }
        User user = new User();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole("USER");
        user.setCreatedAt(LocalDate.now());
        userRepository.save(user);
        
        //todo: change return so obj doesn't contain password
        return user;
    }
}
