package org.example.finalprojectmyshop.user.service.impl;

import org.example.finalprojectmyshop.user.models.entities.User;
import org.example.finalprojectmyshop.user.models.entities.enums.UserRole;
import org.springframework.stereotype.Component;

@Component
public class CurrentUser {
    private User user;

    public boolean isLoggedIn() {
        return this.user != null;
    }

    public boolean isAdmin() {
        return this.user
                .getRoles()
                .stream()
                .anyMatch(role -> role.getRole().equals(UserRole.ADMINISTRATOR));
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
