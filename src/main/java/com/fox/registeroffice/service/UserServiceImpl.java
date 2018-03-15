package com.fox.registeroffice.service;

import com.fox.registeroffice.converter.UserConverter;
import com.fox.registeroffice.domain.User;
import com.fox.registeroffice.dto.UserDto;
import com.fox.registeroffice.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserConverter userConverter;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserConverter userConverter) {
        this.userRepository = userRepository;
        this.userConverter = userConverter;
    }

    @Transactional
    @Override
    public void saveUser(UserDto userDto) {
        User user = userConverter.dtoToEntity(userDto);
        user.setPassword(new BCryptPasswordEncoder(4).encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.delete(userId);
    }

    @Override
    public List<UserDto> getUsersList() {
        return userConverter.entityListToDtoList(userRepository.findAll());
    }

    @Override
    public UserDto findUser(Long userId) {
        return userConverter.entityToDto(userRepository.findOne(userId));
    }


}
