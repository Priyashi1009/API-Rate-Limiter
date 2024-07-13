package com.apirate.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apirate.model.UserLogin;
import com.apirate.repository.UserLoginRepository;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.*;
import java.nio.charset.StandardCharsets;

@Service
public class UserLoginService {

    @Autowired
    private UserLoginRepository userRepository;

    private static final long TOKEN_VALIDITY = 60000; 

    private static final String SECRET = "zyxabczyxabczyxabczyxabczyxabczyxabczyxabczyxabc";

    public String generateJwtToken(String username, String password) {
        boolean isValid = validateUser(username, password);

        if (isValid) {
            Instant now = Instant.now();
            SecretKey key = Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));

            return Jwts.builder()
                    .setSubject(username)
                    .setIssuedAt(Date.from(now))
                    .setExpiration(Date.from(now.plusMillis(TOKEN_VALIDITY)))
                    .signWith(key, SignatureAlgorithm.HS256)
                    .compact();
        }
        return null;
    }

    private boolean validateUser(String username, String password) {
        Optional<UserLogin> userOptional = userRepository.findByUsername(username);

        if (userOptional.isPresent()) {
            UserLogin user = userOptional.get();
            return user.getPassword().equals(password);
        }
        return false;
    }

    public Map<String, String> loginService(String username, String password) {
        String token = generateJwtToken(username, password);
        Map<String, String> response = new HashMap<>();
        if (token != null) {
            response.put("token", token);
        } else {
            response.put("error", "Invalid username or password");
        }
        return response;
    }
    
    public Map<String, Object> validateJwtToken(String token) {

        Map<String, Object> response = new HashMap<>();
        try {
            SecretKey key = Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));
            String subject = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();

            List<String> Restaurents = Arrays.asList("Restaurent1", "Restaurent2", "Restaurent3");
            response.put("Restaurents", Restaurents);
            response.put("username", subject);
        } catch (Exception e) {
        	response.put("error occured", "Token mismatch/Logged out");
        }
        return response;
    }
}