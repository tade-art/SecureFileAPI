package com.tade.secure_file_api.service;

import java.time.LocalDate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.tade.secure_file_api.model.User;
import com.tade.secure_file_api.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public User registerUser(String email, String password){
        if(userRepository.existsByEmail(email)){
            return null; // User already exists
        }
        User user = new User();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole("USER");
        user.setCreatedAt(LocalDate.now());
        userRepository.save(user);
        return user;
    }
}
