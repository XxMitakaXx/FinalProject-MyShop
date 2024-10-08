package org.example.finalprojectmyshop.user.web;

import org.example.finalprojectmyshop.order.models.dtos.imports.SearchProductByNameDTO;
import org.example.finalprojectmyshop.product.models.dtos.exports.CategoryAndRandomProductsDTO;
import org.example.finalprojectmyshop.product.service.CategoryService;
import org.example.finalprojectmyshop.user.models.user.MyShopUserDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.HashSet;
import java.util.Set;

@Controller
public class HomeController {

    private final CategoryService categoryService;
    private Set<CategoryAndRandomProductsDTO> categories = new HashSet<>();

    public HomeController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @ModelAttribute("searchProductByNameDTO")
    public SearchProductByNameDTO searchProductByNameDTO() {
        return new SearchProductByNameDTO();
    }

    @GetMapping("/")
    public String home(Model model, @AuthenticationPrincipal MyShopUserDetails user) {
        this.categories = this.categoryService.getCategoriesWithRandomProducts();

        model.addAttribute("categories", this.categories);
        model.addAttribute("user", user);

        return "home";
    }

}
