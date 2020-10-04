package com.sharemoment.ws.error;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ErrorHandler implements ErrorController {

    //Spring Security ile Kimlik Doğrulama Hata Cevabı

    //UYGULAMA ÇAPINDA TEK BİR YERDE BÜTÜN ERRORLARI APIERROR A ÇEVİRİYOR DURUMA GELDİK.

    @Autowired
    private ErrorAttributes errorAttributes;

    @RequestMapping(path = "/error")
    public ApiError handleError(WebRequest webRequest) {
        Map<String, Object> attributes = errorAttributes.getErrorAttributes(webRequest, true);
        String message = (String) attributes.get("message");
        String path = (String) attributes.get("path");
        int status = (Integer) attributes.get("status");
        ApiError apiError = new ApiError(status, message, path);
        //Message path ve status gibi Validation Fieldlarla ilgili Error Listesini de alabiliyoruz.
        //Ve aldığımız listeyi ApiErrors içerisindeki ValidationErrors mapine dönüştüreceğiz.
        if (attributes.containsKey("errors")) {// NULL POINTER EXCEPTION
            List<FieldError> fieldErrors = (List<FieldError>) attributes.get("errors");
            Map<String, String> validationErrors = new HashMap<>();
            for (FieldError fieldError : fieldErrors) {
                validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            apiError.setValidationErrors(validationErrors);
        }
        return apiError;

        //BÖYLECE @EXCEPTIONHANDLER 'A GEREK KALMADAN, HEM ERROR CONTROLLER VASITASIYLA
        //HEM AUTHENTICATIONDAKI ERRORLARI API ERROR A MODELLEYEN
        //HEM DE VALIDATIONLI FAIL DURUMLARINI APIERRORA MODELLEYEN METOT OLDU.
    }


    @Override
    public String getErrorPath() {
        return "/error";
    }
}
