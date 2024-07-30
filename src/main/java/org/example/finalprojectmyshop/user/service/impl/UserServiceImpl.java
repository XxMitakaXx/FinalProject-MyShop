package org.example.finalprojectmyshop.user.service.impl;

import org.example.finalprojectmyshop.mediaFile.models.entities.MediaFileEntity;
import org.example.finalprojectmyshop.mediaFile.service.ImagesHelperService;
import org.example.finalprojectmyshop.order.models.entities.CartEntity;
import org.example.finalprojectmyshop.order.repository.CartRepository;
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

import java.io.IOException;
import java.util.Optional;

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

        if (optional.isEmpty()) {
            // TODO throw error
        }

        return optional.get();
    }
}
