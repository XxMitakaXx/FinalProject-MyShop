package org.example.finalprojectmyshop.user.service;

import org.example.finalprojectmyshop.user.models.entities.UserRoleEntity;
import org.example.finalprojectmyshop.user.models.entities.enums.UserRole;

public interface UserRoleService {
    UserRoleEntity findByRole(UserRole role);
}
