package com.sharemoment.ws.service;

import com.sharemoment.ws.entity.UserEntity;
import com.sharemoment.ws.error.ApiError;
import com.sharemoment.ws.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public ResponseEntity handleAuthentication(String authorization) {
        if (authorization == null) {
            ApiError apiError = new ApiError(401, "Unauthorized Request", "/api/auth");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(apiError);
        }
        String base64Encoded = authorization.split("Basic ")[1]; //dXN1cjE6UDRzc3dvcmQ=
        String decoded = new String(Base64.getDecoder().decode(base64Encoded)); // userName:password
        String[] parts = decoded.split(":"); // ["userName", "password"]
        String userName = parts[0];
        String password = parts[1];
        UserEntity userInDB = userRepository.findByUserName(userName);
        if (userInDB == null) {
            ApiError apiError = new ApiError(401, "Unauthorized Request", "/api/auth");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(apiError);
        }
        String hashedPassword = userInDB.getPassword();
        if(!passwordEncoder.matches(password,hashedPassword)){
            ApiError apiError = new ApiError(401, "Unauthorized Request", "/api/auth");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(apiError);
        }
        return ResponseEntity.ok().build();

    }

}
