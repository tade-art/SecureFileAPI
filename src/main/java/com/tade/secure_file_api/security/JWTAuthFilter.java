package com.tade.secure_file_api.security;

import java.io.IOException;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.tade.secure_file_api.exception.BadTokenException;
import com.tade.secure_file_api.model.User;
import com.tade.secure_file_api.repository.UserRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

/*
* Filter for JWT authentication, validating tokens and setting security context
*/

@Component
@RequiredArgsConstructor
public class JWTAuthFilter extends OncePerRequestFilter {
    
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    // Filter method to validate JWT token and set authentication context
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        //Grab auth header
        String authHeader = request.getHeader("Authorization");
        // Validate auth header
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            // Extract token and validate
            String token = authHeader.substring(7);
            if (jwtUtil.isTokenValid(token)) {
                // Extract email from token and load user details
                String email = jwtUtil.getEmailFromToken(token);
                User user =  userRepository.findByEmail(email).orElseThrow(() -> new BadTokenException("User not found for token"));
                // Set authentication in security context based on user role
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, user.getRole().equals("ADMIN") ? List.of(() -> "ROLE_ADMIN") : List.of(() -> "ROLE_USER"));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response);
    }
}