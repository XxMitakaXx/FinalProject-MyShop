package org.example.finalprojectmyshop.user.service.impl;

import org.example.finalprojectmyshop.user.models.dtos.UserLoginDTO;
import org.example.finalprojectmyshop.user.models.dtos.UserRegisterDTO;
import org.example.finalprojectmyshop.user.models.entities.User;
import org.example.finalprojectmyshop.user.repository.UserRepository;
import org.example.finalprojectmyshop.user.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void register(UserRegisterDTO userRegisterDTO) {
        User user = this.modelMapper.map(userRegisterDTO, User.class);
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));

        this.userRepository.save(user);
    }

    @Override
    public void login(UserLoginDTO userLoginDTO) {
        User user = this.modelMapper.map(userLoginDTO, User.class);
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));

        this.userRepository.save(user);
    }
}
