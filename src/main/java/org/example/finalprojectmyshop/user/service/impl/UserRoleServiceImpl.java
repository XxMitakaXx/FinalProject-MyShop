package org.example.finalprojectmyshop.user.service.impl;

import org.apache.catalina.User;
import org.example.finalprojectmyshop.user.models.entities.UserRoleEntity;
import org.example.finalprojectmyshop.user.models.entities.enums.UserRole;
import org.example.finalprojectmyshop.user.repository.UserRoleRepository;
import org.example.finalprojectmyshop.user.service.UserRoleService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    private final UserRoleRepository userRoleRepository;

    public UserRoleServiceImpl(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }


    @Override
    public UserRoleEntity findByRole(UserRole role) {
        Optional<UserRoleEntity> optional = this.userRoleRepository.findByRole(role);

        if (optional.isEmpty()) {
            // TODO throw error
        }

        return optional.get();
    }
}
