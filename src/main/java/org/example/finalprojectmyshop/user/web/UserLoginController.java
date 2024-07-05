package org.example.finalprojectmyshop.user.web;

import org.example.finalprojectmyshop.user.models.dtos.UserLoginDTO;
import org.example.finalprojectmyshop.user.service.UserService;
import org.example.finalprojectmyshop.user.service.impl.CurrentUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/users")
public class UserLoginController {

    private final UserService userService;
    private final CurrentUser currentUser;

    public UserLoginController(UserService userService, CurrentUser currentUser) {
        this.userService = userService;
        this.currentUser = currentUser;
    }

    @ModelAttribute("loginUserData")
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
            UserLoginDTO data,
            Model model,
            RedirectAttributes redirectAttributes
    ) {

        if (this.currentUser.isLoggedIn()) {
            return "redirect:/";
        }

        if (currentUser.isLoggedIn()) {
            redirectAttributes.addAttribute("loginData", data);
            redirectAttributes.addAttribute("org.springframework.validation.BindingResult.UserLoginDTO");
            return "redirect:/users/login";
        }

        this.userService.login(data);

        return "redirect:/";
    }
}
