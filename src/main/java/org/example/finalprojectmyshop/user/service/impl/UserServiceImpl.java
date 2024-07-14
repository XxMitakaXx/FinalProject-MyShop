package org.example.finalprojectmyshop.user.service.impl;

import org.example.finalprojectmyshop.mediaFile.models.entities.MediaFile;
import org.example.finalprojectmyshop.mediaFile.models.enums.ImageType;
import org.example.finalprojectmyshop.mediaFile.service.MediaFileService;
import org.example.finalprojectmyshop.user.models.dtos.UserLoginDTO;
import org.example.finalprojectmyshop.user.models.dtos.UserRegisterDTO;
import org.example.finalprojectmyshop.user.models.entities.User;
import org.example.finalprojectmyshop.user.repository.UserRepository;
import org.example.finalprojectmyshop.user.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.awt.*;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final MediaFileService mediaFileService;
    private final CurrentUser currentUser;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, CurrentUser currentUser, MediaFileService mediaFileService, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.currentUser = currentUser;
        this.mediaFileService = mediaFileService;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void register(UserRegisterDTO userRegisterDTO) {
        User user = this.modelMapper.map(userRegisterDTO, User.class);
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));

        String url = this.mediaFileService.upload(userRegisterDTO.getProfilePicture(), ImageType.USER);
        MediaFile mediaFile = new MediaFile();
        mediaFile.setUrl(url);
        this.mediaFileService.save(mediaFile);

        user.setProfilePicture(mediaFile);

        this.userRepository.save(user);
    }

    @Override
    public boolean login(UserLoginDTO userLoginDTO) {
        User user = this.userRepository.findByEmail(userLoginDTO.getEmail());

        if (user == null) {
            // TODO Throw error
            return false;
        }

        if (this.passwordEncoder.matches(userLoginDTO.getPassword(), user.getPassword()) && !this.currentUser.isLoggedIn()) {
            this.currentUser.setUser(user);

            return true;
        }

        return false;
    }

    @Override
    public String downloadProfileImage(MediaFile profilePicture) {
        String fileName = this.mediaFileService.downloadFile(profilePicture.getUrl(), ImageType.USER);

        return fileName;
    }

    @Override
    public void save(User user) {
        this.userRepository.save(user);
    }
}
