package org.example.finalprojectmyshop.user.web;

import org.example.finalprojectmyshop.order.models.dtos.imports.SearchProductByNameDTO;
import org.example.finalprojectmyshop.user.models.dtos.exports.FoundedUsersForDeleteDTO;
import org.example.finalprojectmyshop.user.models.dtos.imports.SearchUserForDeleteDTO;
import org.example.finalprojectmyshop.user.models.entities.UserEntity;
import org.example.finalprojectmyshop.user.service.AdvancedUserService;
import org.example.finalprojectmyshop.user.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class AdminDeleteUserController {

    private final AdvancedUserService advancedUserService;
    private final UserService userService;

    public AdminDeleteUserController(AdvancedUserService advancedUserService, UserService userService) {
        this.advancedUserService = advancedUserService;
        this.userService = userService;
    }

    @ModelAttribute("searchProductByNameDTO")
    public SearchProductByNameDTO searchProductByNameDTO() {
        return new SearchProductByNameDTO();
    }

    @GetMapping("/delete-user")
    public String viewDeleteUser(Model model) {
        SearchUserForDeleteDTO searchUserForDeleteDTO = new SearchUserForDeleteDTO();
        model.addAttribute("searchUserForDeleteDTO", searchUserForDeleteDTO);

        FoundedUsersForDeleteDTO foundedUsersForDeleteDTO = new FoundedUsersForDeleteDTO();
        model.addAttribute("foundedUsersForDeleteDTO", foundedUsersForDeleteDTO);

        return "delete-user";
    }

    @GetMapping("/find-users-for-delete")
    public String viewFindUsersForDelete(SearchUserForDeleteDTO searchUserForDeleteDTO, Model model) {
        model.addAttribute("searchUserForDeleteDTO", searchUserForDeleteDTO);

        FoundedUsersForDeleteDTO foundedUsersForDeleteDTO = this.advancedUserService.findUsersForDelete(searchUserForDeleteDTO.getEmail());
        model.addAttribute("foundedUsersForDeleteDTO", foundedUsersForDeleteDTO);

        return "delete-user";
    }


    @GetMapping("/user-details/{id}")
    public String viewUserDetails(@PathVariable("id") long id, Model model) {
        UserEntity user = this.userService.findUserEntity(id);

        model.addAttribute("user", user);

        return "delete-user-details";
    }

    @DeleteMapping("/delete-user/{id}")
    public String processDeleteUser(@PathVariable("id") long id) {
        this.advancedUserService.deleteUser(id);

        return "redirect:/delete-user";
    }

}
