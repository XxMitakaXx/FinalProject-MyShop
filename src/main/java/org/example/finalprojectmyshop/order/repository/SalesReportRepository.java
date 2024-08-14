package org.example.finalprojectmyshop.order.repository;

import org.example.finalprojectmyshop.order.models.dtos.exports.SalesReport;
import org.example.finalprojectmyshop.order.models.entities.Sale;
import org.example.finalprojectmyshop.order.models.entities.SalesReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Set;

@Repository
public interface SalesReportRepository extends JpaRepository<SalesReportEntity, Long> {
    @Query("FROM SalesReportEntity s WHERE s.date > :date")
    SalesReportEntity findReportForPastDay(Date date);
}
