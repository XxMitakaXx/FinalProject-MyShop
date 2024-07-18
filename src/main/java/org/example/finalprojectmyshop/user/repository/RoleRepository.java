package org.example.finalprojectmyshop.user.repository;

import org.example.finalprojectmyshop.user.models.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
