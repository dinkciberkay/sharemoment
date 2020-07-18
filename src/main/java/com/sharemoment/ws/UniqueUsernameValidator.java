package com.sharemoment.ws;

import com.sharemoment.ws.entity.UserEntity;
import com.sharemoment.ws.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean isValid(String username, ConstraintValidatorContext constraintValidatorContext) {
        UserEntity userEntity = userRepository.findByUserName(username);
        return userEntity == null;
    }
}
