package com.sharemoment.ws.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.sharemoment.ws.Views;
import com.sharemoment.ws.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/api/auth")
    //RequestHeader kullanarak request içindeki hangi headerı alacağımızı söyleyip yakalayabiliriz.
    //Basic Authorization Header'ın değerini yakalıyoruz. Bu header'ın opsiyonel olduğunu söylüyoruz.
    //Bu header olmasa da bizim controllerimiza bu requesti ulaştırması için required=false kullandık.
    //Required olmadığını yazmıştık ancak artık Spring Security ile header gelecek ve buradan fail etme ihtimalimiz yok.
    //O yüzden silebiliriz.
    @JsonView(Views.Base.class)
    //Cevap dönerken oluşturacağın Jsonları Views.Base classına göre oluştur.
    //Böylelikle passworde JsonView içerisinde Base classını göstermezsek Json içinde görünmez olacaktır.
    public ResponseEntity handleAuthentication(@RequestHeader(name = "Authorization") String authorization) {
        return loginService.handleAuthentication(authorization);
    }

    //Springin BadCredentials Exceptionlarını yakaladığında ApiError objesi döndürmesini sağlamaya çalıştık.
    //Ancak öncesinde zaten Spring Security /error pathiyle hata fırlatıyor. Onu da ErrorHandler içinde
    //ApiError obj. döndürerek tasarladık.
    //Bu @ExceptionHandler' ın ÇALIŞMADIĞINI gördük.
//    @ExceptionHandler(BadCredentialsException.class)
//    @ResponseStatus(HttpStatus.UNAUTHORIZED)
//    public ApiError handleBadCredentialsException() {
//        return new ApiError(401, "Unauthorized Request", "/api/auth");
//    }
}
