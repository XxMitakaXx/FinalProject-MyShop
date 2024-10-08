package org.example.finalprojectmyshop.product.repository;

import org.example.finalprojectmyshop.product.models.entities.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
}
