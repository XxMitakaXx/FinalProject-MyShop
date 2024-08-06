package org.example.finalprojectmyshop.product.service.impl;

import org.example.finalprojectmyshop.product.repository.WarrantyRepository;
import org.example.finalprojectmyshop.product.service.WarrantyService;
import org.springframework.stereotype.Service;

@Service
public class WarrantyServiceImpl implements WarrantyService {

    private final WarrantyRepository warrantyRepository;

    public WarrantyServiceImpl(WarrantyRepository warrantyRepository) {
        this.warrantyRepository = warrantyRepository;
    }

    @Override
    public void deleteWarranty(long id) {
        this.warrantyRepository.deleteById(id);
    }
}
