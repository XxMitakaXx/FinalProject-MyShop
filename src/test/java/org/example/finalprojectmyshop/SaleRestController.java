package org.example.finalprojectmyshop;

import org.example.finalprojectmyshop.order.models.entities.Sale;
import org.example.finalprojectmyshop.order.repository.SaleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Date;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class SaleRestController {

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetSalesInfo() throws Exception {
        Sale sale1 = new Sale();
        sale1.setDate(new Date());
        sale1.setSaleSum(1000.00);
        sale1.setCityVillage("Plovdiv");
        sale1.setAddress("Kuchuk Paris");

        this.saleRepository.save(sale1);

        Sale sale2 = new Sale();
        sale2.setDate(new Date());
        sale2.setSaleSum(1000.00);
        sale2.setCityVillage("Plovdiv");
        sale2.setAddress("Kuchuk Paris");

        this.saleRepository.save(sale2);

        Sale sale3 = new Sale();
        sale3.setDate(new Date());
        sale3.setSaleSum(1000.00);
        sale3.setCityVillage("Plovdiv");
        sale3.setAddress("Kuchuk Paris");

        this.saleRepository.save(sale3);

        ResultActions result = this.mockMvc.perform(get("/sales-api/get-all").contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(3)));
    }
}
