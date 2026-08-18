package com.los.applicationservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoanProduct {

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

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;

    private Long createdById;

    private Long modifiedById;

    private Boolean isDeleted;
}