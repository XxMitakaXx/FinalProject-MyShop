package org.example.finalprojectmyshop.user.service;

import org.example.finalprojectmyshop.mediaFile.models.entities.MediaFile;
import org.example.finalprojectmyshop.user.models.dtos.UserLoginDTO;
import org.example.finalprojectmyshop.user.models.dtos.UserRegisterDTO;
import org.example.finalprojectmyshop.user.models.entities.UserEntity;

public interface UserService {
    void register(UserRegisterDTO userRegisterDTO);
    String downloadProfileImage(MediaFile profilePicture);
    void save(UserEntity userEntity);
    UserEntity findUserByEmail(String email);
}
