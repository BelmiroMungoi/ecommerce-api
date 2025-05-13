package com.bbm.ecommerce.dto.response;

import lombok.Data;

@Data
public class SellerResponseDTO {

    private Long id;
    private String email;
    private String storeName;
    private boolean verified;
    private AddressResponseDTO address;
}
