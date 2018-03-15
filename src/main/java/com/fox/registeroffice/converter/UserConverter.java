package com.fox.registeroffice.converter;

import com.fox.registeroffice.domain.User;
import com.fox.registeroffice.dto.UserDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserConverter {

    UserDto entityToDto(User user);

    User dtoToEntity(UserDto userDto);

    List<UserDto> entityListToDtoList(Iterable<User> clients);
}
