package org.example.finalprojectmyshop.order.service;

import org.example.finalprojectmyshop.order.models.dtos.exports.SalesReport;

public interface SalesReportService {
    void makeSalesReport();
    SalesReport getSalesReportForPastDay();
}
