package org.example.finalprojectmyshop.user.service.impl;

import org.example.finalprojectmyshop.user.models.entities.UserEntity;
import org.example.finalprojectmyshop.user.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public class MyShopUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public MyShopUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return this.userRepository
                .findByEmail(email)
                .map(MyShopUserDetailsService::map)
                .orElseThrow(() -> new UsernameNotFoundException("User with email " + email + " not found!"));
    }

    private static UserDetails map(UserEntity user) {
        return User
                .withUsername(user.getEmail())
                .password(user.getPassword())
                .authorities(List.of())
                .disabled(false)
                .build();
    }

}
