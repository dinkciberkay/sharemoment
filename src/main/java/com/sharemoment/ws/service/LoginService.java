package com.sharemoment.ws.service;

import com.sharemoment.ws.configuration.ShareMomentUserDetails;
import com.sharemoment.ws.entity.UserEntity;
import com.sharemoment.ws.mapper.UserMapper;
import com.sharemoment.ws.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class LoginService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

//    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    //    public ResponseEntity handleAuthentication(String authorization) {
    public ResponseEntity handleAuthentication() {
        //Bu metota geldiğinde artık authorization headerının geldiğinden eminiz.
        //Security işlemlerini Springe bıraktık çünkü ve ApiError obj. dönmesini sağladık.
//        if (authorization == null) {
//            ApiError apiError = new ApiError(401, "Unauthorized Request", "/api/auth");
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(apiError);
//        }

        //Bu kısımlara da ihtiyacımız kalmadı. Spring Security sayesinde userName erişiyoruz.
//        String base64Encoded = authorization.split("Basic ")[1]; //dXN1cjE6UDRzc3dvcmQ=
//        String decoded = new String(Base64.getDecoder().decode(base64Encoded)); // userName:password
//        String[] parts = decoded.split(":"); // ["userName", "password"]
//        String userName = parts[0];
//        String password = parts[1];
//        UserEntity userInDB = userRepository.findByUserName(userName);
        //Artık DB de bu User olduğunu biliyoruz ve passwordünde miss match yok.
        //Aksi durumda zaten Spring Securityden fail edecek.
//        if (userInDB == null) {
//            ApiError apiError = new ApiError(401, "Unauthorized Request", "/api/auth");
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(apiError);
//        }
//        String hashedPassword = userInDB.getPassword();
//        if (!passwordEncoder.matches(password, hashedPassword)) {
//            ApiError apiError = new ApiError(401, "Unauthorized Request", "/api/auth");
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(apiError);
//        }

        //Authorization headerına bakmadan Spring Sec. ile userName erişebiliyoruz.
        ShareMomentUserDetails userDetails = (ShareMomentUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //Bu şekilde de kullanılabilir. Extra Query kullanmak yerine Spring Sec. ShareMomentUserDetails ile de
        //userName e erişebiliriz.
//        UserEntity userInDB = userRepository.findByUserName(userName);
//        String userName = userDetails.getUsername();
//        return ResponseEntity.ok(userMapper.entityToDto(userInDB));
        return ResponseEntity.ok(userMapper.entityToDto(userDetails.getUserEntity()));

    }

}
