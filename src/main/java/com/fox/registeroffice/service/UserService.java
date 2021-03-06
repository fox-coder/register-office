package com.fox.registeroffice.service;

import com.fox.registeroffice.dto.UserDto;

import java.util.List;

public interface UserService {

    void saveUser(UserDto userDto);

    void deleteUser(Long userId);

    List<UserDto> getUsersList();

    UserDto findUser(Long userId);
}
