package org.example.finalprojectmyshop.user.web;

import jakarta.validation.Valid;
import org.example.finalprojectmyshop.user.models.dtos.UserRegisterDTO;
import org.example.finalprojectmyshop.user.models.entities.enums.UserRole;
import org.example.finalprojectmyshop.user.service.UserService;
import org.example.finalprojectmyshop.user.service.impl.CurrentUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
//@RequestMapping("/users")
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
    public String viewRegister(Model model) {
        if (this.currentUser.isLoggedIn()) {
            return "redirect:/";
        }

        model.addAttribute("roles", UserRole.values());

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
            redirectAttributes.addFlashAttribute("userRegisterDTO", data);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.UserRegisterDTO");

//            return "redirect:/users/register";
            return "redirect:/register";
        }

        if (!data.getPassword().equals(data.getConfirmPassword())) {
            // TODO handle error
            redirectAttributes.addFlashAttribute("userRegisterDTO", data);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.UserRegisterDTO", bindingResult);

            return "redirect:/register";
        }

        this.userService.register(data);

        return "redirect:/users/login";
    }

}
