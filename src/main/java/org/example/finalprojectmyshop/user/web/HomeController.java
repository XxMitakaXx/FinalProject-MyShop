package org.example.finalprojectmyshop.user.web;

import org.example.finalprojectmyshop.mediaFile.service.MediaFileService;
import org.example.finalprojectmyshop.product.models.dtos.CategoryAndRandomProductsDTO;
import org.example.finalprojectmyshop.product.models.entities.Category;
import org.example.finalprojectmyshop.product.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashSet;
import java.util.Set;

@Controller
public class HomeController {

    private final CategoryService categoryService;
    private final MediaFileService mediaFileService;
    private Set<CategoryAndRandomProductsDTO> categories = new HashSet<>();

    public HomeController(CategoryService categoryService, MediaFileService mediaFileService) {
        this.categoryService = categoryService;
        this.mediaFileService = mediaFileService;
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
