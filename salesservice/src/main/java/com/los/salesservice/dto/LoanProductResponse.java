package com.los.salesservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoanProductResponse {

    private String productCode;
    private String productName;

    private Double minAmount;
    private Double maxAmount;

    private Integer minTenure;
    private Integer maxTenure;

    private Double interestRate;
}