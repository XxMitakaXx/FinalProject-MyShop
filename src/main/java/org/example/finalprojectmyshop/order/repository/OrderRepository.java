package org.example.finalprojectmyshop.order.repository;

import org.example.finalprojectmyshop.order.models.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("FROM Order o WHERE o.logisticStatus != 'RECEIVED'")
    Set<Order> findOrdersForDelivery();
}
