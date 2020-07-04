package com.sharemoment.ws.mapper;

import com.sharemoment.ws.dto.UserDto;
import com.sharemoment.ws.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto entityToDto(UserEntity userEntity);
    UserEntity dtoToEntity(UserDto userDto);
}
