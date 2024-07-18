package org.example.finalprojectmyshop.user.service.impl;

import org.example.finalprojectmyshop.user.models.entities.Role;
import org.example.finalprojectmyshop.user.repository.RoleRepository;
import org.example.finalprojectmyshop.user.service.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void save(Role role) {
        this.roleRepository.save(role);
    }
}
