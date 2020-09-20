package com.sharemoment.ws.service;

import com.sharemoment.ws.GenericResponse;
import com.sharemoment.ws.repository.UserRepository;
import com.sharemoment.ws.dto.UserDto;
import com.sharemoment.ws.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        //SecurityConfiguration içerisinde Bean oluşturarak bir password encoder olusturuyoruz.
        //Spring context içerisinde artık böyle bir instance var ve burada inject edebiliriz.
        this.passwordEncoder = passwordEncoder;
    }

    public GenericResponse createNewUser(UserDto userDto) {
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userRepository.save(userMapper.dtoToEntity(userDto));
        return new GenericResponse("User Created");
    }

}
