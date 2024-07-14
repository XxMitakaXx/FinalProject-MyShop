package org.example.finalprojectmyshop.product.web;

import org.example.finalprojectmyshop.product.models.dtos.ProductDetailsDTO;
import org.example.finalprojectmyshop.product.models.dtos.ProductDetailsSecondaryCategoryDTO;
import org.example.finalprojectmyshop.product.service.ProductService;
import org.example.finalprojectmyshop.product.service.SecondaryCategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ProductDetailController {

    private final ProductService productService;
    private final SecondaryCategoryService secondaryCategoryService;

    public ProductDetailController(ProductService productService, SecondaryCategoryService secondaryCategoryService) {
        this.productService = productService;
        this.secondaryCategoryService = secondaryCategoryService;
    }

    @GetMapping("/product-details/{id}")
    public String viewProductDetails(@PathVariable("id") long id, Model model) {
        ProductDetailsDTO product = this.productService.findById(id);
        ProductDetailsSecondaryCategoryDTO category = this.secondaryCategoryService.findCategoryByProductId(id);

        model.addAttribute("product", product);
        model.addAttribute("category", category);

        return "product-details";
    }
}
