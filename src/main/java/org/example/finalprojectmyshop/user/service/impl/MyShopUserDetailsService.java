package org.example.finalprojectmyshop.user.service.impl;

import org.example.finalprojectmyshop.user.models.entities.UserEntity;
import org.example.finalprojectmyshop.user.models.entities.UserRoleEntity;
import org.example.finalprojectmyshop.user.models.entities.enums.UserRole;
import org.example.finalprojectmyshop.user.models.user.MyShopUserDetails;
import org.example.finalprojectmyshop.user.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public class MyShopUserDetailsService implements UserDetailsService {

    private static final String ROLE_PREFIX = "ROLE_";

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
        return new MyShopUserDetails(
                user.getFirstName(),
                user.getPassword(),
                user.getRoles().stream().map(UserRoleEntity::getRole).map(MyShopUserDetailsService::map).toList(),
                user.getFirstName(),
                user.getLastName()
        );
    }

    private static GrantedAuthority map(UserRole role) {
        return new SimpleGrantedAuthority(ROLE_PREFIX + role);
    }

}
