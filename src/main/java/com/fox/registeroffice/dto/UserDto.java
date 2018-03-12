package com.fox.registeroffice.dto;

import com.fox.registeroffice.domain.Role;
import lombok.Data;

@Data
public class UserDto {

    private Long id;
    private String login;
    private String password;
    private Role role;
}
