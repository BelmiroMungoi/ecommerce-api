package com.bbm.ecommerce.dto.request;

import lombok.Data;

@Data
public class AddressRequestDTO {

    private String street;
    private String city;
    private String province;
    private String country;
    private String zipCode;
}
