package com.los.salesservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationRequest {

    @NotNull
    private Long leadId;

    private Long customerId;

    @NotBlank
    private String loanProduct;

    @NotNull
    @Positive
    private Double loanAmount;

    @NotNull
    @Positive
    private Integer loanTenure;

    @NotNull
    @Positive
    private Double interestRate;

    private String description;
}