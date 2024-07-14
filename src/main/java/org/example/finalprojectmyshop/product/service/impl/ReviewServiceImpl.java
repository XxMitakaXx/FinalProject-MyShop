package org.example.finalprojectmyshop.product.service.impl;

import org.example.finalprojectmyshop.product.models.dtos.AddReviewDTO;
import org.example.finalprojectmyshop.product.models.entities.Product;
import org.example.finalprojectmyshop.product.models.entities.Rating;
import org.example.finalprojectmyshop.product.models.entities.Review;
import org.example.finalprojectmyshop.product.repository.ReviewRepository;
import org.example.finalprojectmyshop.product.service.ProductService;
import org.example.finalprojectmyshop.product.service.RatingService;
import org.example.finalprojectmyshop.product.service.ReviewService;
import org.example.finalprojectmyshop.user.service.impl.CurrentUser;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final ProductService productService;
    private final RatingService ratingService;
    private final CurrentUser currentUser;

    public ReviewServiceImpl(ReviewRepository reviewRepository, ProductService productService, RatingService ratingService, CurrentUser currentUser) {
        this.reviewRepository = reviewRepository;
        this.productService = productService;
        this.ratingService = ratingService;
        this.currentUser = currentUser;
    }

    @Override
    public void save(AddReviewDTO addReviewDTO) {
        Review review = new Review();

        review.setTitle(addReviewDTO.getTitle());

        Rating rating = new Rating();
        rating.setRating(addReviewDTO.getRating());
        this.ratingService.save(rating);

        review.setRating(rating);
        review.setDescription(addReviewDTO.getDescription());
        review.setDate(new Date());
        review.setUser(this.currentUser.getUser());

        Product product = this.productService.findProductEntityById(addReviewDTO.getProductId());
        review.setProduct(product);

        this.reviewRepository.save(review);
    }

}
