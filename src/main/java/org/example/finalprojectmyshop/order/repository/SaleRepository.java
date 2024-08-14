package org.example.finalprojectmyshop.order.repository;

import org.example.finalprojectmyshop.order.models.entities.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Set;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {
    @Query("FROM Sale s WHERE s.date > :date")
    Set<Sale> findSalesForPastDay(Date date);
}
