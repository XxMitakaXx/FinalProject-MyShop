package org.example.finalprojectmyshop.product.models.dtos.exports;

import java.util.ArrayList;
import java.util.List;

public class FoundedProductsByNameDTO {
    List<FoundedProductByNameDTO> foundedProductByNameDTO;

    public FoundedProductsByNameDTO() {
        this.foundedProductByNameDTO = new ArrayList<>();
    }

    public List<FoundedProductByNameDTO> getFoundedProductByNameDTO() {
        return this.foundedProductByNameDTO;
    }

    public void setFoundedProductByNameDTO(List<FoundedProductByNameDTO> foundedProductByNameDTO) {
        this.foundedProductByNameDTO = foundedProductByNameDTO;
    }
}
