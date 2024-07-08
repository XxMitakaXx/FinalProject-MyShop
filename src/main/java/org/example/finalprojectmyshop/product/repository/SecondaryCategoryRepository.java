package org.example.finalprojectmyshop.product.repository;

import org.example.finalprojectmyshop.product.models.entities.SecondaryCategory;
import org.example.finalprojectmyshop.product.models.enums.SecondaryCategoryName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SecondaryCategoryRepository extends JpaRepository<SecondaryCategory, Long> {
    SecondaryCategory findByName(SecondaryCategoryName name);
}
