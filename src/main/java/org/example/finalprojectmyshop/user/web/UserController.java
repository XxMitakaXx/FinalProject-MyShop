package org.example.finalprojectmyshop.user.web;

import jakarta.validation.Valid;
import org.example.finalprojectmyshop.mediaFile.models.entities.MediaFileEntity;
import org.example.finalprojectmyshop.mediaFile.service.ImagesHelperService;
import org.example.finalprojectmyshop.user.models.dtos.UserEditProfileDataDTO;
import org.example.finalprojectmyshop.user.models.entities.UserEntity;
import org.example.finalprojectmyshop.user.service.UserService;
import org.example.finalprojectmyshop.user.service.impl.UserHelperService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@Controller
public class UserController {

    private final UserHelperService userHelperService;
    private final UserService userService;
    private final ImagesHelperService imagesHelperService;

    public UserController(UserHelperService userHelperService, UserService userService, ImagesHelperService imagesHelperService) {
        this.userHelperService = userHelperService;
        this.userService = userService;
        this.imagesHelperService = imagesHelperService;
    }

    @GetMapping("/user-profile")
    public String viewUserProfile(Model model) {

        UserEntity user = this.userHelperService.getUser();
        model.addAttribute("user", user);

        return "user-profile";
    }

    @GetMapping("/edit-user-profile-data")
    public String viewEditUserProfileData(Model model) {
        UserEditProfileDataDTO userEditProfileDataDTO = new UserEditProfileDataDTO();

        UserEntity user = this.userHelperService.getUser();

        userEditProfileDataDTO.setFirstName(user.getFirstName());
        userEditProfileDataDTO.setLastName(user.getLastName());
        userEditProfileDataDTO.setEmail(user.getEmail());
        userEditProfileDataDTO.setPhoneNumber(user.getPhoneNumber());
        userEditProfileDataDTO.setBirthDate(user.getBirthDate());

        model.addAttribute("user", user);
        model.addAttribute("userEditProfileDataDTO", userEditProfileDataDTO);

        return "edit-user-profile-data";
    }

    @PutMapping("/edit-user-profile-data")
    public String processEditUserProfileData(
            @Valid UserEditProfileDataDTO data,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes
    ) throws IOException {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userEditProfileDataDTO", data);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.UserEditProfileDataDTO", bindingResult);

            return "redirect:/edit-user-profile-data";
        }

        UserEntity user = this.userHelperService.getUser();
        user.setFirstName(data.getFirstName());
        user.setLastName(data.getLastName());
        user.setEmail(data.getEmail());
        user.setPhoneNumber(data.getPhoneNumber());
        user.setBirthDate(data.getBirthDate());

        if (!data.getProfilePicture().isEmpty()) {
            MediaFileEntity mediaFileEntity = this.imagesHelperService.saveImage(data.getProfilePicture());
            user.setProfilePicture(mediaFileEntity);
        }

        this.userService.save(user);

        return "redirect:/user-profile";
    }
}
