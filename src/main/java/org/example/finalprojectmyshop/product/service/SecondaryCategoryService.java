package org.example.finalprojectmyshop.product.service;

import org.example.finalprojectmyshop.product.models.dtos.exports.ProductDetailsSecondaryCategoryDTO;
import org.example.finalprojectmyshop.product.models.entities.SecondaryCategory;
import org.example.finalprojectmyshop.product.models.enums.SecondaryCategoryName;

public interface SecondaryCategoryService {
    void save(SecondaryCategory secondaryCategory);
    ProductDetailsSecondaryCategoryDTO findCategoryByProductId(long name);
    SecondaryCategory findSecondaryCategoryEntityByName(SecondaryCategoryName name);
}
