package org.example.finalprojectmyshop.product.service;

import org.example.finalprojectmyshop.product.models.dtos.exports.FoundedProductByNameDTO;
import org.example.finalprojectmyshop.product.models.dtos.exports.ProductDetailsSecondaryCategoryDTO;
import org.example.finalprojectmyshop.product.models.entities.SecondaryCategory;
import org.example.finalprojectmyshop.product.models.enums.SecondaryCategoryName;

import java.util.Set;

public interface SecondaryCategoryService {
    void save(SecondaryCategory secondaryCategory);
    ProductDetailsSecondaryCategoryDTO findCategoryByProductId(long name);
    SecondaryCategory findSecondaryCategoryEntityByName(SecondaryCategoryName name);
    Set<FoundedProductByNameDTO> getProductsByCategory(SecondaryCategoryName secondaryCategoryName);
}
