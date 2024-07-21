package org.example.finalprojectmyshop.product.web;

import jakarta.validation.Valid;
import org.example.finalprojectmyshop.product.models.dtos.imports.AddReviewDTO;
import org.example.finalprojectmyshop.product.service.ReviewService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ReviewController {

    private final ReviewService reviewService;
    private final CurrentUser currentUser;

    public ReviewController(ReviewService reviewService, CurrentUser currentUser) {
        this.reviewService = reviewService;
        this.currentUser = currentUser;
    }

    @ModelAttribute("addReviewDTO")
    public AddReviewDTO addReviewDTO() {
        return new AddReviewDTO();
    }


    @PostMapping("/add-review")
    public String addReview(
            @Valid AddReviewDTO addReviewDTO,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes
    ) {

        if (!this.currentUser.isLoggedIn()) {
            return "redirect:/users/login";
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addReviewDTO", addReviewDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.AddReviewDTO", bindingResult);

            return "redirect:/product-details/" + addReviewDTO.getProductId();
        }

        this.reviewService.save(addReviewDTO);

        return "redirect:http://localhost:8080/product-details/" + addReviewDTO.getProductId();
    }
}
