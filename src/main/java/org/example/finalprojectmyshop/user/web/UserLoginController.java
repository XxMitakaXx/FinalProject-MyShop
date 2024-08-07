package org.example.finalprojectmyshop.user.web;

import jakarta.validation.Valid;
import org.apache.el.parser.BooleanNode;
import org.example.finalprojectmyshop.order.models.dtos.imports.SearchProductByNameDTO;
import org.example.finalprojectmyshop.user.models.dtos.imports.UserLoginDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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

    @ModelAttribute("searchProductByNameDTO")
    public SearchProductByNameDTO searchProductByNameDTO() {
        return new SearchProductByNameDTO();
    }

    @GetMapping("/login")
    public String viewLogin(Model model) {
        model.addAttribute("showErrorMessage", false);

        return "login";
    }

    @GetMapping("/login-error")
    public String viewLoginError(
            @Valid UserLoginDTO data,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userLoginDTO", data);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userLoginDTO", bindingResult);
        }
        return "redirect:/users/login";
    }



}
