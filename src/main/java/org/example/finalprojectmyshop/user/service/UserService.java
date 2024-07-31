package org.example.finalprojectmyshop.user.service;

import org.example.finalprojectmyshop.mediaFile.models.entities.MediaFileEntity;
import org.example.finalprojectmyshop.user.models.dtos.UserRegisterDTO;
import org.example.finalprojectmyshop.user.models.entities.UserEntity;

import java.io.IOException;
import java.util.Set;

public interface UserService {
    void register(UserRegisterDTO userRegisterDTO) throws IOException;
    void save(UserEntity userEntity);
    UserEntity findUserByEmail(String email);
    Set<UserEntity> findAllUsers();
}
