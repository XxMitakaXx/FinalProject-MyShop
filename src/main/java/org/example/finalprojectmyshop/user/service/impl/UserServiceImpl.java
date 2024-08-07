package org.example.finalprojectmyshop.user.service.impl;

import org.example.finalprojectmyshop.mediaFile.models.entities.MediaFileEntity;
import org.example.finalprojectmyshop.mediaFile.service.ImagesHelperService;
import org.example.finalprojectmyshop.order.models.entities.CartEntity;
import org.example.finalprojectmyshop.order.repository.CartRepository;
import org.example.finalprojectmyshop.user.models.dtos.exports.UserDetailsDTO;
import org.example.finalprojectmyshop.user.models.dtos.imports.AdminEditUserProfileDataDTO;
import org.example.finalprojectmyshop.user.models.dtos.imports.UserEditProfileDataDTO;
import org.example.finalprojectmyshop.user.models.dtos.imports.UserRegisterDTO;
import org.example.finalprojectmyshop.user.models.entities.UserEntity;
import org.example.finalprojectmyshop.user.models.entities.UserRoleEntity;
import org.example.finalprojectmyshop.user.models.entities.enums.UserRole;
import org.example.finalprojectmyshop.user.repository.UserRepository;
import org.example.finalprojectmyshop.user.service.UserRoleService;
import org.example.finalprojectmyshop.user.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserRoleService userRoleService;
    private final ImagesHelperService imagesHelperService;
    private final CartRepository cartRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, UserRoleService userRoleService, ImagesHelperService imagesHelperService, CartRepository cartRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userRoleService = userRoleService;
        this.imagesHelperService = imagesHelperService;
        this.cartRepository = cartRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void register(UserRegisterDTO userRegisterDTO) throws IOException {
        UserEntity userEntity = this.modelMapper.map(userRegisterDTO, UserEntity.class);
        userEntity.setPassword(this.passwordEncoder.encode(userEntity.getPassword()));

        MediaFileEntity mediaFile = this.imagesHelperService.saveImage(userRegisterDTO.getProfilePicture());
        userEntity.setProfilePicture(mediaFile);

        CartEntity cart = new CartEntity();
        this.cartRepository.save(cart);
        userEntity.setCart(cart);

        UserRoleEntity role = this.userRoleService.findByRole(UserRole.USER);
        userEntity.getRoles().add(role);

        this.userRepository.save(userEntity);
    }

    @Override
    public void save(UserEntity userEntity) {
        this.userRepository.save(userEntity);
    }

    @Override
    public UserEntity findUserByEmail(String email) {
        Optional<UserEntity> optional = this.userRepository.findByEmail(email);

        return optional.orElseGet(UserEntity::new);

    }

    @Override
    public Set<UserEntity> findAllUsers() {
        return new HashSet<>(this.userRepository.findAll());
    }

    @Override
    public void editUserProfileData(UserEntity user, UserEditProfileDataDTO newData) throws IOException {
        user.setFirstName(newData.getFirstName());
        user.setLastName(newData.getLastName());
        user.setPhoneNumber(newData.getPhoneNumber());

        if (newData.getBirthDate() != null) {
            user.setBirthdate(newData.getBirthDate());
        }


        if (newData.getProfilePicture().getBytes().length != 0) {
            MediaFileEntity mediaFileEntity = this.imagesHelperService.saveImage(newData.getProfilePicture());
            user.setProfilePicture(mediaFileEntity);
        }

        this.save(user);
    }

    @Override
    public void adminEditUserProfileData(UserEntity user, AdminEditUserProfileDataDTO newData) throws IOException {
        Optional<UserEntity> optional = this.userRepository.findByEmail(newData.getEmail());

        if (optional.isEmpty() || optional.get().getId() ==  user.getId()) {
            user.setFirstName(newData.getFirstName());
            user.setLastName(newData.getLastName());
            user.setEmail(newData.getEmail());
            user.setPhoneNumber(newData.getPhoneNumber());

            if (newData.getBirthDate() != null) {
                user.setBirthdate(newData.getBirthDate());
            }

            if (newData.getProfilePicture().getBytes().length != 0) {
                MediaFileEntity mediaFileEntity = this.imagesHelperService.saveImage(newData.getProfilePicture());
                user.setProfilePicture(mediaFileEntity);
            }

            this.save(user);
        }
    }

    @Override
    public List<UserEntity> findUsersByEmail(String email) {
        return this.userRepository.findUsersByEmail(email);
    }

    @Override
    public UserDetailsDTO findUserDetails(long id) {
        Optional<UserEntity> optional = this.userRepository.findById(id);

        if (optional.isPresent()) {
            UserDetailsDTO userDetailsDTO = new UserDetailsDTO();
            UserEntity userEntity = optional.get();

            userDetailsDTO.setFirstName(userEntity.getFirstName());
            userDetailsDTO.setLastName(userEntity.getLastName());
            userDetailsDTO.setEmail(userEntity.getEmail());
            userDetailsDTO.setPhoneNumber(userEntity.getPhoneNumber());
            userDetailsDTO.setBirthdate(userEntity.getBirthdate());
            userDetailsDTO.setProfilePictureUrl(userEntity.getProfilePicture().getImageUrl());

            return userDetailsDTO;
        }

        return new UserDetailsDTO();
    }

    @Override
    public UserEntity findUserEntity(long id) {
        Optional<UserEntity> optional = this.userRepository.findById(id);

        return optional.orElseGet(UserEntity::new);

    }

    @Override
    public void deleteUserEntity(long id) {
        this.userRepository.deleteById(id);
    }
}
