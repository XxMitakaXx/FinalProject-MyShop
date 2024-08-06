package org.example.finalprojectmyshop.user.repository;

import org.example.finalprojectmyshop.user.models.dtos.exports.FoundedUserForDeleteDTO;
import org.example.finalprojectmyshop.user.models.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);

    @Query("FROM UserEntity ue WHERE ue.email like %:email%")
    List<UserEntity> findUsersByEmail(String email);
}
