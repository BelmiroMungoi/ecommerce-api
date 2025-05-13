package com.bbm.ecommerce.service;

import com.bbm.ecommerce.dto.request.SellerRequestDTO;
import com.bbm.ecommerce.dto.response.EcomResponse;
import com.bbm.ecommerce.dto.response.SellerResponseDTO;
import com.bbm.ecommerce.model.Seller;

import java.util.List;

public interface SellerService {

    EcomResponse createSeller(SellerRequestDTO requestDTO);

    Seller getSellerById(Long id);

    List<SellerResponseDTO> getAllSellers();

    EcomResponse updateSeller(Seller seller);
}
