package org.example.finalprojectmyshop.product.service;

import org.example.finalprojectmyshop.product.models.dtos.ProductDetailsSecondaryCategoryDTO;
import org.example.finalprojectmyshop.product.models.enums.SecondaryCategoryName;

public interface SecondaryCategoryService {
    ProductDetailsSecondaryCategoryDTO findCategoryByProductId(long name);
}
