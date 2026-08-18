package com.los.applicationservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoanProductResponse {
    private Long productId;

    private String productName;

    private String productScheme;

    private String productSchemeCode;

    private Long subProductId;

    private String loanCategory;

    private BigDecimal minAmount;

    private BigDecimal maxAmount;

    private Integer minTenure;

    private Integer maxTenure;

    private Boolean isActive;

}