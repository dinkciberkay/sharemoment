package com.sharemoment.ws.controller;

import com.sharemoment.ws.dto.UserDto;
import com.sharemoment.ws.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/api/users")
    public void createNewUser(@RequestBody UserDto userDto) {
        userService.createNewUser(userDto);
        log.info(userDto.toString());
    }
}
