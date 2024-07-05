package org.example.finalprojectmyshop.product.repository;

import org.example.finalprojectmyshop.product.models.entities.SecondaryCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecondaryCategoryRepository extends JpaRepository<SecondaryCategory, Long> {
}
