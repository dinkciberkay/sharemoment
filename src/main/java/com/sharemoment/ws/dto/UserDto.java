package com.sharemoment.ws.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.sharemoment.ws.UniqueUsername;
import com.sharemoment.ws.Views;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class UserDto {

    @NotNull
    @Size(min = 4, max = 255)
    @UniqueUsername
    @JsonView(Views.Base.class)
    private String userName;

    @NotNull
    @Size(min = 4, max = 255)
    @JsonView(Views.Base.class)
    private String displayName;

    @NotNull
    @Size(min = 8, max = 255)
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$")
//  @JsonIgnore//Json oluştururken bu fieldı görmezden gel. Request içerisinde password gözükmemesi için.
//  JsonIgnore bizim post işlemlerinde gönderirken json içerisinde olmayacağından password'ü okuyamayacak.
//    Bunun yerine JsonView ile field ları gruplayarak yapabiliriz.
    @JsonView(Views.Sensitive.class)
    private String password;

    @JsonView(Views.Base.class)
    private String image;
}
