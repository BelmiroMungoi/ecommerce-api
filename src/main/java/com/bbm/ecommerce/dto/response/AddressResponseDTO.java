package com.bbm.ecommerce.dto.response;

import lombok.Data;

@Data
public class AddressResponseDTO {

    private Long id;
    private String street;
    private String city;
    private String province;
    private String country;
    private String zipCode;
}
