package org.example.finalprojectmyshop.user.web;

import jakarta.validation.Valid;
import org.example.finalprojectmyshop.user.models.dtos.exports.FoundedUsersForEditDTO;
import org.example.finalprojectmyshop.user.models.dtos.imports.SearchUserForEditDTO;
import org.example.finalprojectmyshop.user.models.dtos.imports.UserEditProfileDataDTO;
import org.example.finalprojectmyshop.user.models.entities.UserEntity;
import org.example.finalprojectmyshop.user.service.AdvancedUserService;
import org.example.finalprojectmyshop.user.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@Controller
public class AdminEditUserDataController {

    private final AdvancedUserService advancedUserService;
    private final UserService userService;

    public AdminEditUserDataController(AdvancedUserService advancedUserService, UserService userService) {
        this.advancedUserService = advancedUserService;
        this.userService = userService;
    }

    @GetMapping("/edit-user")
    public String viewEditUser(Model model) {
        SearchUserForEditDTO searchUserForEditDTO = new SearchUserForEditDTO();
        model.addAttribute("searchUserForEditDTO", searchUserForEditDTO);

        FoundedUsersForEditDTO foundedUsersForEditDTO = new FoundedUsersForEditDTO();
        model.addAttribute("foundedUsersForEditDTO", foundedUsersForEditDTO);

        return "edit-user";
    }

    @GetMapping("/find-users-for-edit")
    public String viewEditUser(SearchUserForEditDTO searchUserForEditDTO, Model model) {
        model.addAttribute("searchUserForEditDTO", searchUserForEditDTO);

        if (searchUserForEditDTO.getEmail().isBlank()) {
            return "redirect:/edit-user";
        }

        FoundedUsersForEditDTO foundedUsersForEditDTO = this.advancedUserService.findUsersForEdit(searchUserForEditDTO.getEmail());
        model.addAttribute("foundedUsersForEditDTO", foundedUsersForEditDTO);

        return "edit-user";
    }

    @GetMapping("/edit-user-details/{id}")
    public String viewEditUserDetails(@PathVariable Long id, Model model) {

        UserEntity userEntity = this.userService.findUserEntity(id);
        model.addAttribute("user", userEntity);

        return "edit-user-details";
    }

    @GetMapping("/admin-edit-user-profile-data/{id}")
    public String viewAdminEditUserProfileData(@PathVariable("id") long id, Model model) {
        UserEditProfileDataDTO userEditProfileDataDTO = new UserEditProfileDataDTO();

        this.userService.findUserEntity(id);
        UserEntity user = this.userService.findUserEntity(id);

        userEditProfileDataDTO.setFirstName(user.getFirstName());
        userEditProfileDataDTO.setLastName(user.getLastName());
        userEditProfileDataDTO.setEmail(user.getEmail());
        userEditProfileDataDTO.setPhoneNumber(user.getPhoneNumber());
        userEditProfileDataDTO.setBirthDate(user.getBirthdate());

        model.addAttribute("user", user);
        model.addAttribute("userEditProfileDataDTO", userEditProfileDataDTO);
        model.addAttribute("id", id);

        return "admin-edit-user-profile-data";
    }

    @PutMapping("/admin-edit-user-profile-data/{id}")
    public String processAdminEditUserProfileData(
            @PathVariable("id") long id,
            @Valid UserEditProfileDataDTO data,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes
    ) throws IOException {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userEditProfileDataDTO", data);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.UserEditProfileDataDTO", bindingResult);

            return "redirect:/admin-edit-user-profile-data";
        }

        UserEntity user = this.userService.findUserEntity(id);
        this.userService.editUserProfileData(user, data);

        return "redirect:/admin-view-user-profile-data/" + id;
    }

}
