package com.fox.registeroffice.security;

import com.fox.registeroffice.domain.User;
import com.fox.registeroffice.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository repository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final User user = repository.findByLogin(username);
        if (null == user) {
            throw new UsernameNotFoundException(username);
        }
        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority(user.getRole().name())));
    }


}
