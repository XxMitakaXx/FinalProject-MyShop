package org.example.finalprojectmyshop.user.service.impl;

import org.example.finalprojectmyshop.user.models.entities.UserEntity;
import org.example.finalprojectmyshop.user.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserHelperService {

    private final UserService userService;

    public UserHelperService(UserService userService) {
        this.userService = userService;
    }

    public UserEntity getUser() {
        return this.userService.findUserByEmail(this.getUserDetails().getUsername());
    }

    public UserDetails getUserDetails() {
        return (UserDetails) this.getAuthentication().getPrincipal();
    }

    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
