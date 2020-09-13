package com.sharemoment.ws;

import com.sharemoment.ws.dto.UserDto;
import com.sharemoment.ws.entity.UserEntity;
import com.sharemoment.ws.repository.UserRepository;
import com.sharemoment.ws.service.UserService;
import liquibase.pro.packaged.B;
import liquibase.pro.packaged.U;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class WsApplication {

    public static void main(String[] args) {
        SpringApplication.run(WsApplication.class, args);
    }

    //Uygulama ayağa kalkarken DB'ye inital data oluşturmak için kullanılabilir.
//    @Bean
//    CommandLineRunner createInitialUser(UserService userService) {
//        return args -> {
//            UserDto userDto = new UserDto();
//            userDto.setUserName("initialUserName");
//            userDto.setDisplayName("initialDisplayName");
//            userDto.setPassword("initialUserPassword");
//            userService.createNewUser(userDto);
//        };
//    }

}
