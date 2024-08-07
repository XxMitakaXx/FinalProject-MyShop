package org.example.finalprojectmyshop.user.web;

import jakarta.validation.Valid;
import org.example.finalprojectmyshop.mediaFile.service.ImagesHelperService;
import org.example.finalprojectmyshop.user.models.dtos.imports.UserEditProfileDataDTO;
import org.example.finalprojectmyshop.user.models.entities.UserEntity;
import org.example.finalprojectmyshop.user.service.AdvancedUserService;
import org.example.finalprojectmyshop.user.service.UserService;
import org.example.finalprojectmyshop.user.service.ValidateUserHelperService;
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
    private final ValidateUserHelperService validateUserHelperService;

    public UserController(UserHelperService userHelperService, UserService userService, ValidateUserHelperService validateUserHelperService) {
        this.userHelperService = userHelperService;
        this.userService = userService;
        this.validateUserHelperService = validateUserHelperService;
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
        userEditProfileDataDTO.setBirthDate(user.getBirthdate());

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
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userEditProfileDataDTO", bindingResult);

            return "redirect:/edit-user-profile-data";
        }

        UserEntity dbUser = this.userService.findUserByEmail(data.getEmail());
        UserEntity user = this.userHelperService.getUser();

        if (
                Integer.parseInt(String.valueOf(dbUser.getId())) == 0 ||
                        Integer.parseInt(String.valueOf(dbUser.getId())) == Integer.parseInt(String.valueOf(user.getId()))
        ) {
            this.userService.editUserProfileData(user, data);
        }

        return "redirect:/user-profile";
    }
}
