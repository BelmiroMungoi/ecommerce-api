package com.bbm.ecommerce.dto.request;

import lombok.Data;

@Data
public class SellerRequestDTO {

    private String email;
    private String storeName;
    private AddressRequestDTO storeAddress;
}
