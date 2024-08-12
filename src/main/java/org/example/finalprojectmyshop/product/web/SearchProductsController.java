package org.example.finalprojectmyshop.product.web;

import org.example.finalprojectmyshop.order.models.dtos.imports.SearchProductByNameDTO;
import org.example.finalprojectmyshop.product.models.dtos.exports.FoundedProductsByNameDTO;
import org.example.finalprojectmyshop.product.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SearchProductsController {

    private final ProductService productService;

    public SearchProductsController(ProductService productService) {
        this.productService = productService;
    }

    @ModelAttribute("searchProductByNameDTO")
    public SearchProductByNameDTO searchProductByNameDTO() {
        return new SearchProductByNameDTO();
    }

    @GetMapping ("/find-products-by-name")
    public String findProductsByName(SearchProductByNameDTO searchProductByNameDTO, Model model) {

        if (!searchProductByNameDTO.getName().isBlank()) {
            FoundedProductsByNameDTO products = this.productService.findProductsByName(searchProductByNameDTO.getName());

            model.addAttribute("products", products);
        } else {
            model.addAttribute("products", new FoundedProductsByNameDTO());
        }

        return "search-products";
    }
}
