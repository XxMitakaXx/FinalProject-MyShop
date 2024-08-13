package org.example.finalprojectmyshop.order.service.impl;

import org.example.finalprojectmyshop.order.models.dtos.exports.SaleInfoDTO;
import org.example.finalprojectmyshop.order.models.dtos.exports.SaleInfoProductDTO;
import org.example.finalprojectmyshop.order.models.dtos.exports.SalesInfoDTO;
import org.example.finalprojectmyshop.order.models.entities.Order;
import org.example.finalprojectmyshop.order.models.entities.Sale;
import org.example.finalprojectmyshop.order.repository.SaleRepository;
import org.example.finalprojectmyshop.order.service.SaleService;
import org.example.finalprojectmyshop.product.models.entities.Product;
import org.example.finalprojectmyshop.user.models.entities.UserEntity;
import org.example.finalprojectmyshop.user.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class SaleServiceImpl implements SaleService {

//    private final String GET_ALL_SALES_API_URL = ;

    private final SaleRepository saleRepository;
    private final RestClient restClient;
    private final RestTemplate restTemplate;

    public SaleServiceImpl(SaleRepository saleRepository, RestClient restClient, RestTemplate restTemplate) {
        this.saleRepository = saleRepository;
        this.restClient = restClient;
        this.restTemplate = restTemplate;
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
        sale.setSum(order.getPriceForSum());
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

        saleInfoDTO.setSum(sale.getSum());
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
}
