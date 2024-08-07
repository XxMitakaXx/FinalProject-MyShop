package org.example.finalprojectmyshop.product.web;

import jakarta.validation.Valid;
import org.example.finalprojectmyshop.order.models.dtos.imports.SearchProductByNameDTO;
import org.example.finalprojectmyshop.product.models.dtos.exports.ReviewDTO;
import org.example.finalprojectmyshop.product.models.dtos.imports.AddReviewDTO;
import org.example.finalprojectmyshop.product.models.entities.Product;
import org.example.finalprojectmyshop.product.service.ProductService;
import org.example.finalprojectmyshop.product.service.ReviewService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Set;

@Controller
public class ReviewController {

    private final ReviewService reviewService;
    private final ProductService productService;

    public ReviewController(ReviewService reviewService, ProductService productService) {
        this.reviewService = reviewService;
        this.productService = productService;
    }

    @ModelAttribute("addReviewDTO")
    public AddReviewDTO addReviewDTO() {
        return new AddReviewDTO();
    }

    @ModelAttribute("searchProductByNameDTO")
    public SearchProductByNameDTO searchProductByNameDTO() {
        return new SearchProductByNameDTO();
    }

    @GetMapping("/add-review/{id}")
    private String viewAddReview(@PathVariable("id") long id, Model model) {
        Product product = productService.findProductEntityById(id);

        model.addAttribute("product", product);

        return "add-review";
    }

    @PostMapping("/add-review/{id}")
    public String processAddReview(
            @PathVariable("id") long id,
            @Valid AddReviewDTO addReviewDTO,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes
            ) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addReviewDTO", addReviewDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.AddReviewDTO", bindingResult);

            return "redirect:/product-details/" + id;
        }

        this.reviewService.save(addReviewDTO, id);

        return "redirect:http://localhost:8080/product-details/" + id;
    }

    @DeleteMapping("/delete-review/{id}")
    public String processDeleteReview(@PathVariable("id") long id) {
        this.reviewService.deleteReview(id);

        return "redirect:/user-reviews";
    }

    @GetMapping("/user-reviews")
    public String viewReview(Model model) {
        Set<ReviewDTO> userReviews = this.reviewService.findAll();

        model.addAttribute("userReviews", userReviews);

        return "user-reviews";
    }
}
