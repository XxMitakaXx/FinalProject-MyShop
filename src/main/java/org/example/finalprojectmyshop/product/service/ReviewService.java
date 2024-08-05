package org.example.finalprojectmyshop.product.service;

import org.example.finalprojectmyshop.product.models.dtos.exports.ReviewDTO;
import org.example.finalprojectmyshop.product.models.dtos.imports.AddReviewDTO;
import org.example.finalprojectmyshop.product.models.entities.Review;

import java.util.Set;

public interface ReviewService {
    void save(AddReviewDTO addReviewDTO, long id);
    Set<ReviewDTO> findAll();
    void deleteReview(long id);
    void deleteReview(Review review);
}
