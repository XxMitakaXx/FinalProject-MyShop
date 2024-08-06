package org.example.finalprojectmyshop.product.repository;

import org.example.finalprojectmyshop.product.models.entities.Warranty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WarrantyRepository extends JpaRepository<Warranty, Long> {
}
