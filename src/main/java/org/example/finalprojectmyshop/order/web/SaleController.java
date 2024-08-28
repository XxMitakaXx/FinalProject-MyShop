package org.example.finalprojectmyshop.order.web;

import org.example.finalprojectmyshop.order.models.dtos.exports.SaleInfoDTO;
import org.example.finalprojectmyshop.order.models.dtos.imports.SearchProductByNameDTO;
import org.example.finalprojectmyshop.order.service.SaleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/sales")
public class SaleController {

    private final SaleService saleService;

    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @ModelAttribute("searchProductByNameDTO")
    public SearchProductByNameDTO searchProductByNameDTO() {
        return new SearchProductByNameDTO();
    }

    @GetMapping("/get-all")
    public String getAllSales(Model model) {
        List<SaleInfoDTO> sales = this.saleService.getSales();

        model.addAttribute("sales", sales);

        return "sales";
    }
}
