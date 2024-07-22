package org.example.finalprojectmyshop.user.service.impl;

import org.example.finalprojectmyshop.mediaFile.models.entities.MediaFile;
import org.example.finalprojectmyshop.mediaFile.models.enums.ImageType;
import org.example.finalprojectmyshop.mediaFile.service.MediaFileService;
import org.example.finalprojectmyshop.user.models.dtos.UserRegisterDTO;
import org.example.finalprojectmyshop.user.models.entities.UserEntity;
import org.example.finalprojectmyshop.user.models.entities.UserRoleEntity;
import org.example.finalprojectmyshop.user.models.entities.enums.UserRole;
import org.example.finalprojectmyshop.user.repository.UserRepository;
import org.example.finalprojectmyshop.user.service.UserRoleService;
import org.example.finalprojectmyshop.user.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserRoleService userRoleService;
    private final MediaFileService mediaFileService;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, UserRoleService userRoleService, MediaFileService mediaFileService, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userRoleService = userRoleService;
        this.mediaFileService = mediaFileService;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void register(UserRegisterDTO userRegisterDTO) {
        UserEntity userEntity = this.modelMapper.map(userRegisterDTO, UserEntity.class);
        userEntity.setPassword(this.passwordEncoder.encode(userEntity.getPassword()));

        String url = this.mediaFileService.upload(userRegisterDTO.getProfilePicture(), ImageType.USER);
        MediaFile mediaFile = new MediaFile();
        mediaFile.setUrl(url);
        this.mediaFileService.save(mediaFile);

        userEntity.setProfilePicture(mediaFile);

        UserRoleEntity role = this.userRoleService.findByRole(UserRole.USER);
        userEntity.getRoles().add(role);

        this.userRepository.save(userEntity);
    }

    @Override
    public String downloadProfileImage(MediaFile profilePicture) {
        String fileName = this.mediaFileService.downloadFile(profilePicture.getUrl(), ImageType.USER);

        return fileName;
    }

    @Override
    public void save(UserEntity userEntity) {
        this.userRepository.save(userEntity);
    }

    @Override
    public UserEntity findUserByEmail(String email) {
        Optional<UserEntity> optional = this.userRepository.findByEmail(email);

        if (optional.isEmpty()) {
            // TODO throw error
        }

        return optional.get();
    }
}
