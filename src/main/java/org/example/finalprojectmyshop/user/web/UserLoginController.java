package org.example.finalprojectmyshop.user.web;

import jakarta.validation.Valid;
import org.example.finalprojectmyshop.mediaFile.models.enums.ImageType;
import org.example.finalprojectmyshop.user.models.dtos.UserLoginDTO;
import org.example.finalprojectmyshop.user.service.UserService;
import org.example.finalprojectmyshop.user.service.impl.CurrentUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
//@RequestMapping("/users")
public class UserLoginController {

    private final UserService userService;
    private final CurrentUser currentUser;

    public UserLoginController(UserService userService, CurrentUser currentUser) {
        this.userService = userService;
        this.currentUser = currentUser;
    }

    @ModelAttribute("userLoginDTO")
    public UserLoginDTO userLoginDTO() {
        return new UserLoginDTO();
    }

    @GetMapping("/login")
    public String viewLogin() {

        if (this.currentUser.isLoggedIn()) {
            return "redirect:/";
        }

        return "login";
    }

    @PostMapping("/login")
    public String processLogin(
            @Valid UserLoginDTO data,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes
    ) {

        if (this.currentUser.isLoggedIn()) {
            return "redirect:/";
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userLoginDTO", data);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.UserLoginDTO", bindingResult);

            return "redirect:/login";
        }

        boolean success = this.userService.login(data);

        if (!success) {
            redirectAttributes.addFlashAttribute("userLoginDTO", data);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.UserLoginDTO", bindingResult);
            return "redirect:/login";
        }

       this.userService.downloadProfileImage(this.currentUser.getUser().getProfilePicture());


        return "redirect:/";
    }

    @PostMapping("/logout")
    public String processLogout() {

        if (this.currentUser.isLoggedIn()) {
            this.currentUser.setUser(null);
        }

        return "redirect:/";
    }

}
