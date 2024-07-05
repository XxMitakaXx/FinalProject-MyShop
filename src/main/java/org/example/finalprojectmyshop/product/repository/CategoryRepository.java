package org.example.finalprojectmyshop.product.repository;

import org.example.finalprojectmyshop.product.models.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("FROM Category")
    Set<Category> getAll();
}
