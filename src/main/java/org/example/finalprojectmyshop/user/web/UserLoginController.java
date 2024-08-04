package org.example.finalprojectmyshop.user.web;

import org.example.finalprojectmyshop.user.models.dtos.UserLoginDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/users")
public class UserLoginController {

    @ModelAttribute("loginData")
    public UserLoginDTO userData() {
        return new UserLoginDTO();
    }

    @GetMapping("/login")
    public String viewLogin(Model model) {
        model.addAttribute("showErrorMessage", false);

        return "login";
    }

    @GetMapping("/login-error")
    public String viewLoginError(
            Model model,
            RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("showErrorMessage", true);

        return "redirect:/users/login";
    }



}
