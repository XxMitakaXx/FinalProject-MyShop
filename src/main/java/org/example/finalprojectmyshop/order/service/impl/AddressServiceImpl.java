package org.example.finalprojectmyshop.order.service.impl;

import org.example.finalprojectmyshop.order.repository.AddressRepository;
import org.example.finalprojectmyshop.order.service.AddressService;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {

    private AddressRepository addressRepository;

    @Override
    public void deleteAddress(long id) {
        this.addressRepository.deleteById(id);
    }
}
