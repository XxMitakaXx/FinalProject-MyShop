package org.example.finalprojectmyshop.user.web;

import jakarta.validation.Valid;
import org.example.finalprojectmyshop.user.models.dtos.imports.UserRegisterDTO;
import org.example.finalprojectmyshop.user.models.entities.enums.UserRole;
import org.example.finalprojectmyshop.user.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@Controller
@RequestMapping("/users")
public class UserRegisterController {

    private final UserService userService;

    public UserRegisterController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("userRegisterDTO")
    public UserRegisterDTO userRegisterDTO() {
        return new UserRegisterDTO();
    }

    @GetMapping("/register")
    public String viewRegister(Model model) {
        model.addAttribute("roles", UserRole.values());

        return "register";
    }

    @PostMapping("/register")
    public String processRegister(
            @Valid UserRegisterDTO data,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes
    ) throws IOException {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userRegisterDTO", data);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterDTO", bindingResult);

            return "redirect:/users/register";
        }

        if (!data.getPassword().equals(data.getConfirmPassword())) {
            // TODO handle error
            redirectAttributes.addFlashAttribute("userRegisterDTO", data);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterDTO", bindingResult);

            return "redirect:/users/register";
        }

        this.userService.register(data);

        return "redirect:/users/login";
    }

}
