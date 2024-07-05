package org.example.finalprojectmyshop.user.web;

import jakarta.validation.Valid;
import org.example.finalprojectmyshop.user.models.dtos.UserRegisterDTO;
import org.example.finalprojectmyshop.user.service.UserService;
import org.example.finalprojectmyshop.user.service.impl.CurrentUser;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/users")
public class UserRegisterController {

    private final UserService userService;
    private final CurrentUser currentUser;

    public UserRegisterController(UserService userService, CurrentUser currentUser) {
        this.userService = userService;
        this.currentUser = currentUser;
    }

    @ModelAttribute("userRegisterDTO")
    public UserRegisterDTO userRegisterDTO() {
        return new UserRegisterDTO();
    }

    @GetMapping("/register")
    public String viewRegister() {
        if (this.currentUser.isLoggedIn()) {
            return "redirect:/";
        }

        return "register";
    }

    @PostMapping("/register")
    public String processRegister(
            @Valid UserRegisterDTO data,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes
    ) {
        if (this.currentUser.isLoggedIn()) {
            return "redirect:/";
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("registerData", data);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.UserRegisterDTO");

            return "redirect:/users/register";
        }

        if (!data.getPassword().equals(data.getConfirmPassword())) {
            // TODO handle error
        }

        this.userService.register(data);

        return "redirect:/users/login";
    }

}
