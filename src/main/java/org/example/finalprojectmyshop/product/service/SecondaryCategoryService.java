package org.example.finalprojectmyshop.product.service;

import org.example.finalprojectmyshop.product.models.dtos.exports.ProductDetailsSecondaryCategoryDTO;

public interface SecondaryCategoryService {
    ProductDetailsSecondaryCategoryDTO findCategoryByProductId(long name);
}
