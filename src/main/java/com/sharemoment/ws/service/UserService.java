package com.sharemoment.ws.service;

import com.sharemoment.error.ApiError;
import com.sharemoment.ws.GenericResponse;
import com.sharemoment.ws.UserRepository;
import com.sharemoment.ws.dto.UserDto;
import com.sharemoment.ws.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public ResponseEntity<?> createNewUser(UserDto userDto) {
        ApiError apiError = new ApiError(400, "Validation Error", "/api/users");
        Map<String, String> validationError = new HashMap<>();
        String username = userDto.getUserName();
        String displayName = userDto.getDisplayName();

        if (username == null || username.isEmpty()) {
            validationError.put("userName", "Username Cannot Be Null !");
        }

        if (displayName == null || displayName.isEmpty()) {
            validationError.put("displayName", "Display Name Cannot Be Null !");
        }

        if (validationError.size() > 0) {
            apiError.setValidationErrors(validationError);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
        }


        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userRepository.save(userMapper.dtoToEntity(userDto));
        return ResponseEntity.ok(new GenericResponse("User Created"));
    }

}
