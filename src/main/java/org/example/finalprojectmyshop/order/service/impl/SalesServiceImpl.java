package org.example.finalprojectmyshop.order.service.impl;

import org.example.finalprojectmyshop.order.models.dtos.exports.*;
import org.example.finalprojectmyshop.order.models.entities.Order;
import org.example.finalprojectmyshop.order.models.entities.Sale;
import org.example.finalprojectmyshop.order.models.entities.SalesReportEntity;
import org.example.finalprojectmyshop.order.repository.SalesReportRepository;
import org.example.finalprojectmyshop.order.repository.SaleRepository;
import org.example.finalprojectmyshop.order.service.SalesReportService;
import org.example.finalprojectmyshop.order.service.SaleService;
import org.example.finalprojectmyshop.product.models.entities.Product;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class SalesServiceImpl implements SaleService, SalesReportService {

//    private final String GET_ALL_SALES_API_URL = ;

    private final SaleRepository saleRepository;
    private final RestClient restClient;
    private final RestTemplate restTemplate;
    private final SalesReportRepository salesReportRepository;

    public SalesServiceImpl(SaleRepository saleRepository, RestClient restClient, RestTemplate restTemplate, SalesReportRepository salesReportRepository) {
        this.saleRepository = saleRepository;
        this.restClient = restClient;
        this.restTemplate = restTemplate;
        this.salesReportRepository = salesReportRepository;
    }

    @Override
    public void save(Sale sale) {
        this.saleRepository.save(sale);
    }

    @Override
    public void save(Order order) {
        Sale sale = new Sale();

        sale.setDate(new Date());

        order.getProducts().forEach(product -> sale.getProducts().add(product));
        sale.setSaleSum(order.getPriceForSum());
        sale.setCityVillage(order.getCityVillage());
        sale.setAddress(order.getAddress());

        this.saleRepository.save(sale);
    }

    @Override
    public SalesInfoDTO findSalesInfo() {
        List<Sale> sales = this.saleRepository.findAll();
        SalesInfoDTO salesInfoDTO = new SalesInfoDTO();

        sales.forEach(sale -> {
            SaleInfoDTO saleInfoDTO = this.toSaleInfoDTO(sale);
            salesInfoDTO.getSalesInfos().add(saleInfoDTO);
        });

        return salesInfoDTO;
    }

    @Override
    public boolean deleteSale(long id) {
        this.saleRepository.deleteById(id);

        Optional<Sale> optional = this.saleRepository.findById(id);

        return optional.isEmpty();
    }

    @Override
    public SalesInfoDTO getSales() {
        return restClient
                .get()
                .uri("http://localhost:8080/sales-api/get-all")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(SalesInfoDTO.class);
    }

    private SaleInfoDTO toSaleInfoDTO(Sale sale) {
        SaleInfoDTO saleInfoDTO = new SaleInfoDTO();

        saleInfoDTO.setId(sale.getId());
        saleInfoDTO.setDate(sale.getDate());

        sale.getProducts()
                .forEach(product -> {
                    SaleInfoProductDTO saleInfoProductDTO = this.toSaleInfoProductDTO(product);
                    saleInfoDTO.getProducts().add(saleInfoProductDTO);
                });

        saleInfoDTO.setSum(sale.getSaleSum());
        saleInfoDTO.setCityVillage(sale.getCityVillage());
        saleInfoDTO.setAddress(sale.getAddress());

        return saleInfoDTO;
    }

    private SaleInfoProductDTO toSaleInfoProductDTO(Product product) {
        SaleInfoProductDTO saleInfoProductDTO = new SaleInfoProductDTO();

        saleInfoProductDTO.setName(product.getName());
        saleInfoProductDTO.setPrice(product.getPrice());
        saleInfoProductDTO.setImageUrl(product.getMainImage().getImageUrl());

        return saleInfoProductDTO;
    }

    @Override
    public void makeSalesReport() {
        SalesReportEntity salesReportEntity = new SalesReportEntity();

        Date date = Date.from(LocalDate.now().minusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant());
        Set<Sale> salesForPastDay = this.saleRepository.findSalesForPastDay(date);

        double incomeFromSales = salesForPastDay
                .stream()
                .map(Sale::getSaleSum)
                .reduce(Double::sum)
                .orElse(0.0);

        salesForPastDay.forEach(sale -> {
            sale.getProducts().forEach(product -> {
                boolean contain = salesReportEntity
                        .getProducts()
                        .stream()
                        .map(Product::getId)
                        .collect(Collectors.toSet())
                        .contains(product.getId());

                if (!contain) {
                    salesReportEntity.getProducts().add(product);
                }
            });
        });

        salesReportEntity.setSalesCount(salesForPastDay.size());
        salesReportEntity.setIncomeFromSales(incomeFromSales);
        salesReportEntity.setDate(new Date());

        this.salesReportRepository.save(salesReportEntity);
    }

    @Override
    public SalesReport getSalesReportForPastDay() {
        Date date = Date.from(LocalDate.now().minusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant());
        SalesReportEntity salesReportEntity = this.salesReportRepository.findReportForPastDay(date);
        SalesReport salesReport = this.toSalesReport(salesReportEntity);

        return salesReport;
    }

    private SalesReport toSalesReport(SalesReportEntity salesReportEntity) {
        SalesReport salesReport = new SalesReport();

        salesReport.setSalesCount(salesReportEntity.getSalesCount());
        salesReport.setIncomeFromSales(salesReportEntity.getIncomeFromSales());

        salesReportEntity.getProducts()
                        .forEach(product -> {
                            SalesReportProduct saleReportProduct = this.toSaleReportProduct(product);
                            salesReport.getProducts().add(saleReportProduct);
                        });

        salesReport.setDate(salesReportEntity.getDate());

        return salesReport;
    }

    private SalesReportProduct toSaleReportProduct(Product product) {
        return new SalesReportProduct(
                product.getId(),
                product.getName(),
                product.getMainImage().getImageUrl()
        );
    }
}
