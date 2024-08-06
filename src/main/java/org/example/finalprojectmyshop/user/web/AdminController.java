package org.example.finalprojectmyshop.user.web;

import org.example.finalprojectmyshop.user.models.dtos.exports.UserDetailsDTO;
import org.example.finalprojectmyshop.user.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin-view-user-profile-data/{id}")
    public String viewAdminViewUserProfileData(@PathVariable("id") long id, Model model) {
        UserDetailsDTO userDetailsDTO = this.userService.findUserDetails(id);
        model.addAttribute("userDetailsDTO", userDetailsDTO);

        return "user-details";
    }
}
