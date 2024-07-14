package org.example.finalprojectmyshop.product.web;

import jakarta.validation.Valid;
import org.example.finalprojectmyshop.product.models.dtos.AddReviewDTO;
import org.example.finalprojectmyshop.product.service.ReviewService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @ModelAttribute("addReviewDTO")
    public AddReviewDTO addReviewDTO() {
        return new AddReviewDTO();
    }

    @PostMapping("/add-review-form/{id}")
    public String viewAddReview(@PathVariable("id") long id) {
        this.addReviewDTO().setProductId(id);
        return "add-review";
    }

    @PostMapping("/add-review")
    public String addReview(@Valid AddReviewDTO addReviewDTO) {

        this.reviewService.save(addReviewDTO);

        return "redirect:/product-details/" + addReviewDTO.getProductId();
    }
}
