package org.example.finalprojectmyshop.product.web;

import jakarta.validation.Valid;
import org.example.finalprojectmyshop.product.models.dtos.imports.AddReviewDTO;
import org.example.finalprojectmyshop.product.models.entities.Product;
import org.example.finalprojectmyshop.product.service.ProductService;
import org.example.finalprojectmyshop.product.service.ReviewService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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


    @GetMapping("/add-review/{id}")
    private String viewAddReview(@PathVariable("id") long id, Model model) {
        Product product = productService.findProductEntityById(id);

        model.addAttribute("product", product);

        return "add-review";
    }

    @PostMapping("/add-review/{id}")
    public String addReview(
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

    @GetMapping("/reviews")
    public String viewReview(Model model) {
        this.reviewService.findAll();
    }
}
