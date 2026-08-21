package com.los.applicationservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "loan_product")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoanProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_scheme")
    private String productScheme;

    @Column(name = "product_scheme_code")
    private String productSchemeCode;

    @Column(name = "sub_product_id")
    private Long subProductId;

    @Column(name = "loan_category")
    private String loanCategory;

    @Column(name = "min_amount", precision = 19, scale = 2)
    private BigDecimal minAmount;

    @Column(name = "max_amount", precision = 19, scale = 2)
    private BigDecimal maxAmount;

    @Column(name = "min_tenure")
    private Integer minTenure;

    @Column(name = "max_tenure")
    private Integer maxTenure;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;

    @Column(name = "created_by_id")
    private Long createdById;

    @Column(name = "modified_by_id")
    private Long modifiedById;

    @Column(name = "is_deleted")
    private Boolean isDeleted;
}