package com.tade.secure_file_api.security;

import java.security.Key;
import java.util.Date;
import org.springframework.stereotype.Component;

import com.tade.secure_file_api.exception.BadTokenException;
import com.tade.secure_file_api.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

/*
* Utility class for JWT token operations
*/
@Component
public class JwtUtil {

    private final Key key = Keys.hmacShaKeyFor("tadas-super-secret-jwt-key-which-is-long-enough-123".getBytes());
    private final long expirationMs = 3600000; // 1 hour

    public String generateToken(User user) {
        return Jwts.builder()
                .setSubject(user.getEmail()) // unique identifier
                .claim("role", user.getRole())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationMs))
                .signWith(key)
                .compact();
    }

    //Method to get email from token
    public String getEmailFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    //Method to validate token (check signature and expiration)
    public boolean isTokenValid(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            throw new BadTokenException("Invalid or expired token");
        }
    }
}