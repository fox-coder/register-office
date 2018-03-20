package com.fox.registeroffice.service.impl;

import com.fox.registeroffice.dto.UserDto;
import com.fox.registeroffice.service.UserService;
import com.fox.registeroffice.service.UserServiceProxy;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceProxyImpl implements UserServiceProxy {

    private final UserService userService;

    @Autowired
    public UserServiceProxyImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    @HystrixCommand(commandKey = "SaveUser")
    public void saveUser(UserDto userDto) {
        userService.saveUser(userDto);
    }

    @Override
    @HystrixCommand(commandKey = "DeleteUser")
    public void deleteUser(Long userId) {
        userService.deleteUser(userId);
    }

    @Override
    @HystrixCommand(commandKey = "GetUsersList")
    public List<UserDto> getUsersList() {
        return userService.getUsersList();
    }

    @Override
    @HystrixCommand(commandKey = "FindUser")
    public UserDto findUser(Long userId) {
        return userService.findUser(userId);
    }
}
