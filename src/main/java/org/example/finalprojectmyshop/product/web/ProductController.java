package org.example.finalprojectmyshop.product.web;

import jakarta.validation.Valid;
import org.example.finalprojectmyshop.product.models.dtos.exports.CategoryAndRandomProductsDTO;
import org.example.finalprojectmyshop.product.models.dtos.imports.AddProductDTO;
import org.example.finalprojectmyshop.product.models.dtos.imports.AddProductPropertyDTO;
import org.example.finalprojectmyshop.product.models.enums.SecondaryCategoryName;
import org.example.finalprojectmyshop.product.service.CategoryService;
import org.example.finalprojectmyshop.product.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Set;

@Controller
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final CurrentUser currentUser;

    public ProductController(ProductService productService, CategoryService categoryService, CurrentUser currentUser) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.currentUser = currentUser;
    }

    @ModelAttribute("addProductDTO")
    public AddProductDTO addProductDTO() {
        AddProductDTO addProductDTO = new AddProductDTO();

        for (int i = 1; i <= 40; i++) {
            addProductDTO.getProperties().add(new AddProductPropertyDTO());
        }

        return addProductDTO;
    }

    @ModelAttribute("secondaryCategories")
    public SecondaryCategoryName[] categories() {
        return SecondaryCategoryName.values();
    }

    @GetMapping("/add-product")
    public String viewAddProduct() {
        return "add-product";
    }

    @PostMapping("/add-product")
    public String processAddProduct(
            @Valid AddProductDTO addProductDTO,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes,
            Model model ) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addProductDTO", addProductDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.AddProductDTO", bindingResult);
            return "redirect:/";
        }


        this.productService.save(addProductDTO);

        Set<CategoryAndRandomProductsDTO> categories = this.categoryService.getCategoriesWithRandomProducts();
        model.addAttribute("categories", categories);

        return "redirect:/";
    }

    @PostMapping("/add-to-favorites/{id}")
    private String processAddProductToFavorites(@PathVariable("id") long id) {

        if (!this.currentUser.isLoggedIn()) {
            return "redirect:/login";
        }

        this.productService.addProductToFavorites(id);

        return "redirect:/";
    }
}
