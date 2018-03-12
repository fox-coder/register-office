package com.fox.registeroffice.converter;

import com.fox.registeroffice.domain.User;
import com.fox.registeroffice.dto.UserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserConverter {

    UserDto entityToDto(User user);

    User dtoToEntity(UserDto userDto);
}
