package org.example.finalprojectmyshop;

import org.example.finalprojectmyshop.mediaFile.models.entities.MediaFileEntity;
import org.example.finalprojectmyshop.product.models.entities.Product;
import org.example.finalprojectmyshop.product.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Set;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductDetailsControllerIT {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetProductById() throws Exception {
        Product actualEntity = new Product();
        actualEntity.setId(1L);
        actualEntity.setName("Iphone 15 Pro");
        actualEntity.setPrice(2000.00);
        actualEntity.setDiscountPrice(0.00);
        actualEntity.setMainImage(new MediaFileEntity().setImageUrl("test_url").setImageId("test_id"));
        actualEntity.setImages(
                Set.of(
                        new MediaFileEntity().setImageUrl("test_url").setImageId("test_id"),
                        new MediaFileEntity().setImageUrl("test_url").setImageId("test_id"),
                        new MediaFileEntity().setImageUrl("test_url").setImageId("test_id"),
                        new MediaFileEntity().setImageUrl("test_url").setImageId("test_id"),
                        new MediaFileEntity().setImageUrl("test_url").setImageId("test_id")
                )
        );
        actualEntity.setExpressShip(false);
        actualEntity.setLeasing(false);
        actualEntity.setQuantity(100);

        this.productRepository.save(actualEntity);

        this.mockMvc.perform(get("/product-details/1", actualEntity.getId()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
