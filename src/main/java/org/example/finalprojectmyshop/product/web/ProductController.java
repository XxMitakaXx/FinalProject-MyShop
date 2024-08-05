package org.example.finalprojectmyshop.product.web;

import jakarta.validation.Valid;
import org.example.finalprojectmyshop.product.models.dtos.exports.CategoryAndRandomProductsDTO;
import org.example.finalprojectmyshop.product.models.dtos.exports.FavoriteProductDTO;
import org.example.finalprojectmyshop.product.models.dtos.exports.FoundedProductsForDeleteDTO;
import org.example.finalprojectmyshop.product.models.dtos.imports.AddProductDTO;
import org.example.finalprojectmyshop.product.models.dtos.imports.AddProductPropertyDTO;
import org.example.finalprojectmyshop.product.models.dtos.imports.SearchProductForDeleteDTO;
import org.example.finalprojectmyshop.product.models.enums.SecondaryCategoryName;
import org.example.finalprojectmyshop.product.service.AdvancedProductService;
import org.example.finalprojectmyshop.product.service.CategoryService;
import org.example.finalprojectmyshop.product.service.ProductService;
import org.example.finalprojectmyshop.user.service.UserService;
import org.example.finalprojectmyshop.user.service.impl.UserHelperService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.Set;

@Controller
public class ProductController {

    private final ProductService productService;
    private final AdvancedProductService advancedProductService;
    private final CategoryService categoryService;
    private final UserHelperService userHelperService;
    private final UserService userService;

    public ProductController(ProductService productService, AdvancedProductService advancedProductService, CategoryService categoryService, UserHelperService userHelperService, UserService userService) {
        this.productService = productService;
        this.advancedProductService = advancedProductService;
        this.categoryService = categoryService;
        this.userHelperService = userHelperService;
        this.userService = userService;
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
            Model model) throws IOException {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addProductDTO", addProductDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.AddProductDTO", bindingResult);
            return "redirect:/";
        }


        this.productService.save(addProductDTO);

        Set<CategoryAndRandomProductsDTO> categories = this.categoryService.getCategoriesWithRandomProducts();
        redirectAttributes.addFlashAttribute("categories", categories);

        return "redirect:/";
    }

    @PostMapping("/add-product-to-cart/{id}")
    public String processAddProductToCart(@PathVariable("id") long id) {

        this.productService.addProductToCart(id);

        return "redirect:/cart";
    }

    @GetMapping("/delete-product")
    public String processDeleteProduct(Model model) {
        SearchProductForDeleteDTO searchProductForDeleteDTO = new SearchProductForDeleteDTO();
        model.addAttribute("searchProductForDeleteDTO", searchProductForDeleteDTO);

        FoundedProductsForDeleteDTO foundedProductsForDeleteDTO = new FoundedProductsForDeleteDTO();
        model.addAttribute("foundedProductsForDeleteDTO", foundedProductsForDeleteDTO);

        return "delete-product";
    }

    @GetMapping("/find-products-for-delete")
    public String processFindProductsForDelete(SearchProductForDeleteDTO searchProductForDeleteDTO, Model model) {
        model.addAttribute("searchProductForDeleteDTO", searchProductForDeleteDTO);

        FoundedProductsForDeleteDTO foundedProductsForDeleteDTO = this.productService.searchProductsForDelete(searchProductForDeleteDTO.getName());
        model.addAttribute("foundedProductsForDeleteDTO", foundedProductsForDeleteDTO);

        return "delete-product";
    }

    @DeleteMapping("/delete-product/{id}")
    public String processDeleteProduct(@PathVariable("id") long id, Model model) {
        this.advancedProductService.deleteProduct(id);

        return "redirect:/delete-product";
    }

    @DeleteMapping("/delete-product-from-cart/{id}")
    public String processDeleteProductFromCart(@PathVariable("id") long id) {
        this.productService.deleteProductFromCartByProduct(id);

        return "redirect:/cart";
    }

    @GetMapping("/user-favorites")
    public String viewUserFavorites(Model model) {
        Set<FavoriteProductDTO> favorites = this.productService.findFavoriteProducts();

        model.addAttribute("userFavorites", favorites);

        return "favorites";
    }

    @PostMapping("/add-product-to-favorites/{id}")
    public String processAddProductToFavorites(@PathVariable("id") long id) {
        this.productService.addProductToFavorites(id);

        return "redirect:/user-favorites";
    }

    @DeleteMapping("/delete-product-from-favorites/{id}")
    public String processDeleteProductFromFavorite(@PathVariable("id") long id) {
        this.productService.deleteProductFromFavorites(id);

        return "redirect:/user-favorites";
    }
}
