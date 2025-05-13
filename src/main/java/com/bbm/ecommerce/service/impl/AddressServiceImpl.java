package com.bbm.ecommerce.service.impl;

import com.bbm.ecommerce.model.Address;
import com.bbm.ecommerce.model.User;
import com.bbm.ecommerce.repository.AddressRepository;
import com.bbm.ecommerce.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    @Override
    @Transactional
    public Address createAddress(Address address) {
        return addressRepository.save(address);
    }

    @Override
    public Address getAddressByUser(User user) {
        return null;
    }

    @Override
    public Address updateAddress(Address address) {
        return null;
    }
}
