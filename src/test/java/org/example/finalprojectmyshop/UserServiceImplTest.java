package org.example.finalprojectmyshop;

import org.example.finalprojectmyshop.mediaFile.service.ImagesHelperService;
import org.example.finalprojectmyshop.order.repository.CartRepository;
import org.example.finalprojectmyshop.user.models.dtos.imports.UserRegisterDTO;
import org.example.finalprojectmyshop.user.models.entities.UserEntity;
import org.example.finalprojectmyshop.user.repository.UserRepository;
import org.example.finalprojectmyshop.user.service.UserRoleService;
import org.example.finalprojectmyshop.user.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.IOException;
import java.time.LocalDate;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    private UserServiceImpl toTest;

    @Captor
    private ArgumentCaptor<UserEntity> userEntityCaptor;

    @Mock
    private UserRepository mockUserRepository;

    @Mock
    private UserRoleService mockUserRoleService;

    @Mock
    private ImagesHelperService mockImageHelperService;

    @Mock
    private CartRepository mockCartRepository;

    @Mock
    private PasswordEncoder mockPasswordEncoder;

    @Mock
    private ModelMapper mockModelMapper;


    @BeforeEach
    void setUp() {
        this.toTest = new UserServiceImpl(
                this.mockUserRepository,
                this.mockUserRoleService,
                this.mockImageHelperService,
                this.mockCartRepository,
                this.mockModelMapper,
                this.mockPasswordEncoder
        );
    }


    @Test
    public void testUserRegister() throws IOException {
        UserRegisterDTO userRegisterDTO = new UserRegisterDTO();
        userRegisterDTO.setFirstName("Dimitar");
        userRegisterDTO.setLastName("Varbanov");
        userRegisterDTO.setEmail("mitakavarbanov05@gmail.com");
        userRegisterDTO.setPassword("0911");
        userRegisterDTO.setConfirmPassword("0911");
        userRegisterDTO.setPhoneNumber("0987654321");
        userRegisterDTO.setBirthDate(LocalDate.now());

        Mockito.when(this.mockPasswordEncoder.encode(userRegisterDTO.getPassword()))
                .thenReturn(userRegisterDTO.getPassword() + userRegisterDTO.getPassword());

        Mockito.when(this.mockModelMapper.map(userRegisterDTO, UserEntity.class))
                .thenReturn(
                        new UserEntity(
                                userRegisterDTO.getFirstName(),
                                userRegisterDTO.getLastName(),
                                userRegisterDTO.getEmail(),
                                userRegisterDTO.getPassword(),
                                userRegisterDTO.getPhoneNumber(),
                                userRegisterDTO.getBirthDate()
                        )
                );

        this.toTest.register(userRegisterDTO);

        Mockito.verify(this.mockUserRepository).save(this.userEntityCaptor.capture());

        UserEntity actualSaveduserEntity = this.userEntityCaptor.getValue();

        Assertions.assertEquals(userRegisterDTO.getFirstName(), actualSaveduserEntity.getFirstName());
        Assertions.assertEquals(userRegisterDTO.getLastName(), actualSaveduserEntity.getLastName());
        Assertions.assertEquals(userRegisterDTO.getEmail(), actualSaveduserEntity.getEmail());
        Assertions.assertEquals(userRegisterDTO.getPassword() + userRegisterDTO.getPassword(), actualSaveduserEntity.getPassword());
        Assertions.assertEquals(userRegisterDTO.getPhoneNumber(), actualSaveduserEntity.getPhoneNumber());
    }
}
