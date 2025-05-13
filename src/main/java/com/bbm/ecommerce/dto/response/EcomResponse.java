package com.bbm.ecommerce.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EcomResponse {

    private int code;
    private HttpStatus status;
    private String message;
    private String description;
    private LocalDateTime createdAt;
}
