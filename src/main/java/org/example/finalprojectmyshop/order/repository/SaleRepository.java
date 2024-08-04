package org.example.finalprojectmyshop.order.repository;

import org.example.finalprojectmyshop.order.models.entities.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {
}
