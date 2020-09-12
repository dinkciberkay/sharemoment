package com.sharemoment.ws.controller;

import com.sharemoment.ws.entity.UserEntity;
import com.sharemoment.ws.error.ApiError;
import com.sharemoment.ws.repository.UserRepository;
import com.sharemoment.ws.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;

@RestController
public class AuthController {

    @Autowired
    private AuthService authService;


    @PostMapping("/api/auth")
    //RequestHeader kullanarak request içindeki hangi headerı alacağımızı söyleyip yakalayabiliriz.
    //Basic Authorization Header'ın değerini yakalıyoruz. Bu header'ın opsiyonel olduğunu söylüyoruz.
    //Bu header olmasa da bizim controllerimiza bu requesti ulaştırması için required=false kullandık.
    public ResponseEntity<?> handleAuthentication(@RequestHeader(name = "Authorization", required = false) String authorization) {
        return authService.handleAuthentication(authorization);
    }
}
