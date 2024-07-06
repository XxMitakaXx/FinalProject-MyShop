package org.example.finalprojectmyshop.user.service;

import org.example.finalprojectmyshop.user.models.dtos.UserLoginDTO;
import org.example.finalprojectmyshop.user.models.dtos.UserRegisterDTO;

public interface UserService {
    void register(UserRegisterDTO userRegisterDTO);
    boolean login(UserLoginDTO userLoginDTO);
}
