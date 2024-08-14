package org.example.finalprojectmyshop.order.web;

import org.example.finalprojectmyshop.order.models.dtos.exports.SalesReport;
import org.example.finalprojectmyshop.order.models.dtos.imports.SearchProductByNameDTO;
import org.example.finalprojectmyshop.order.service.SalesReportService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class SaleReportController {

    private final SalesReportService salesReportService;

    public SaleReportController(SalesReportService salesReportService) {
        this.salesReportService = salesReportService;
    }

    @ModelAttribute("searchProductByNameDTO")
    public SearchProductByNameDTO searchProductByNameDTO() {
        return new SearchProductByNameDTO();
    }

    @GetMapping("sales-report")
    public String viewSalesReport(Model model) {
        SalesReport salesReportForPastDay = this.salesReportService.getSalesReportForPastDay();
        model.addAttribute("report", salesReportForPastDay);

        return "sales-report";
    }
}
