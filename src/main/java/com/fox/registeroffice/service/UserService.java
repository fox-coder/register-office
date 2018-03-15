package com.fox.registeroffice.service;

import com.fox.registeroffice.dto.UserDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserService {

    @Transactional
    void saveUser(UserDto userDto);

    void deleteUser(Long userId);

    List<UserDto> getUsersList();

    UserDto findUser(Long userId);
}
