package com.sharemoment.ws.error;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

@RestController
public class ErrorHandler implements ErrorController {

    //Spring Security ile Kimlik Doğrulama Hata Cevabı

    @Autowired
    private ErrorAttributes errorAttributes;

    @RequestMapping(path = "/error")
    public ApiError handleError(WebRequest webRequest) {
        Map<String, Object> attributes = errorAttributes.getErrorAttributes(webRequest, true);
        String message = (String) attributes.get("message");
        String path = (String) attributes.get("path");
        int status = (Integer) attributes.get("status");
        return new ApiError(status, message, path);
    }


    @Override
    public String getErrorPath() {
        return "/error";
    }
}
