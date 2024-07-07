package org.example.finalprojectmyshop.product.web;

import jakarta.validation.Valid;
import org.example.finalprojectmyshop.product.models.dtos.AddProductDTO;
import org.example.finalprojectmyshop.product.models.enums.CategoryName;
import org.example.finalprojectmyshop.product.models.enums.SecondaryCategoryName;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ProductController {

    @ModelAttribute("addProductDTO")
    public AddProductDTO addProductDTO() {
        return new AddProductDTO();
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
            RedirectAttributes redirectAttributes
    ) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addProductDTO", addProductDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.AddProductDTO", bindingResult);
            return "redirect:/add-product";
        }



        System.out.println();
        return "redirect:/";
    }
}
