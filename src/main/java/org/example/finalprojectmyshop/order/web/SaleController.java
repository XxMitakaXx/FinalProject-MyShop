package org.example.finalprojectmyshop.order.web;

import org.example.finalprojectmyshop.order.models.dtos.exports.SalesInfoDTO;
import org.example.finalprojectmyshop.order.service.SaleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Objects;

@Controller
@RequestMapping("/sales")
public class SaleController {

    private final SaleService saleService;

    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @GetMapping("/get-all")
    public String getAllSales(Model model) {
        SalesInfoDTO sales = this.saleService.getSales();

        model.addAttribute("sales", Objects.requireNonNullElseGet(sales, SalesInfoDTO::new));

        return "sales";
    }
}
