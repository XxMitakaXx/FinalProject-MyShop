package org.example.finalprojectmyshop.user.web;

import org.example.finalprojectmyshop.product.models.dtos.exports.CategoryAndRandomProductsDTO;
import org.example.finalprojectmyshop.product.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashSet;
import java.util.Set;

@Controller
public class HomeController {

    private final CategoryService categoryService;
    private Set<CategoryAndRandomProductsDTO> categories = new HashSet<>();

    public HomeController(CategoryService categoryService) {
        this.categoryService = categoryService;

    }

    @GetMapping("/")
    public String home(Model model) {

        if (categories.isEmpty()) {
            this.categories = this.categoryService.getCategoriesWithRandomProducts();
        }

        model.addAttribute("categories", categories);

        return "home";
    }

}
