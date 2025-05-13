package com.bbm.ecommerce.service;

import com.bbm.ecommerce.model.Address;
import com.bbm.ecommerce.model.User;

public interface AddressService {

    Address createAddress(Address address);

    Address getAddressByUser(User user);

    Address updateAddress(Address address);
}
