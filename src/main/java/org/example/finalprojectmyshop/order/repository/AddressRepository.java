package org.example.finalprojectmyshop.order.repository;

import org.example.finalprojectmyshop.order.models.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}
