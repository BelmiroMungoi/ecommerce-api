package com.bbm.ecommerce.service.impl;

import com.bbm.ecommerce.dto.request.AddressRequestDTO;
import com.bbm.ecommerce.dto.request.SellerRequestDTO;
import com.bbm.ecommerce.dto.response.EcomResponse;
import com.bbm.ecommerce.dto.response.SellerResponseDTO;
import com.bbm.ecommerce.mapper.EntityConverter;
import com.bbm.ecommerce.model.Address;
import com.bbm.ecommerce.model.Seller;
import com.bbm.ecommerce.repository.SellerRepository;
import com.bbm.ecommerce.service.AddressService;
import com.bbm.ecommerce.service.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@Service
@RequiredArgsConstructor
public class SellerServiceImpl implements SellerService {

    private final SellerRepository sellerRepository;
    private final AddressService addressService;
    private final EntityConverter<Seller, SellerRequestDTO> sellerConverter;
    private final EntityConverter<Address, AddressRequestDTO> addressConverter;
    private final EntityConverter<Seller, SellerResponseDTO> sellerResponseConverter;

    @Override
    @Transactional
    public EcomResponse createSeller(SellerRequestDTO requestDTO) {
        if (sellerRepository.existsByEmail(requestDTO.getEmail())) {
            throw new IllegalArgumentException("There is already a seller with this email!");
        }
        var address = addressConverter.mapDtoToEntity(requestDTO.getStoreAddress(), Address.class);
        var seller = sellerConverter.mapDtoToEntity(requestDTO, Seller.class);
        var savedAddress = addressService.createAddress(address);
        seller.setAddress(savedAddress);
        sellerRepository.save(seller);

        return EcomResponse.builder()
                .code(CREATED.value())
                .status(CREATED)
                .message("Seller created successfully!")
                .description(seller.getStoreName())
                .createdAt(LocalDateTime.now())
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public Seller getSellerById(Long id) {
        return sellerRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("There is no seller with this id!"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<SellerResponseDTO> getAllSellers() {
        return sellerResponseConverter.mapEntityToDtoList(
                sellerRepository.findAllSellers(), SellerResponseDTO.class);
    }

    @Override
    @Transactional
    public EcomResponse updateSeller(Seller seller) {
        return null;
    }
}
