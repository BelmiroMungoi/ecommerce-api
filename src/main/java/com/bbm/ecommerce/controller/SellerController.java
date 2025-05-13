package com.bbm.ecommerce.controller;

import com.bbm.ecommerce.dto.request.SellerRequestDTO;
import com.bbm.ecommerce.dto.response.EcomResponse;
import com.bbm.ecommerce.dto.response.SellerResponseDTO;
import com.bbm.ecommerce.mapper.EntityConverter;
import com.bbm.ecommerce.model.Seller;
import com.bbm.ecommerce.service.SellerService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/sellers")
@Tag(name = "Seller")
public class SellerController {

    private final SellerService sellerService;

    @PostMapping("/")
    public ResponseEntity<EcomResponse> createSeller(@RequestBody SellerRequestDTO requestDTO) {
        return new ResponseEntity<>(sellerService.createSeller(requestDTO), HttpStatus.CREATED );
    }

    @GetMapping
    public ResponseEntity<List<SellerResponseDTO>> getAllSellers() {
        return ResponseEntity.ok(sellerService.getAllSellers());
    }

}
