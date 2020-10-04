package com.sharemoment.ws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
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
