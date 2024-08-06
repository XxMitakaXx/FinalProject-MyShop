package org.example.finalprojectmyshop.user.service;

import org.example.finalprojectmyshop.user.models.dtos.exports.UserDetailsDTO;
import org.example.finalprojectmyshop.user.models.dtos.imports.UserEditProfileDataDTO;
import org.example.finalprojectmyshop.user.models.dtos.imports.UserRegisterDTO;
import org.example.finalprojectmyshop.user.models.entities.UserEntity;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface UserService {
    void register(UserRegisterDTO userRegisterDTO) throws IOException;
    void save(UserEntity userEntity);
    UserEntity findUserByEmail(String email);
    Set<UserEntity> findAllUsers();
    void editUserProfileData(UserEntity user, UserEditProfileDataDTO newData) throws IOException;
    List<UserEntity> findUsersByEmail(String email);
    UserDetailsDTO findUserDetails(long id);
    UserEntity findUserEntity(long id);
    void deleteUserEntity(long id);
}
