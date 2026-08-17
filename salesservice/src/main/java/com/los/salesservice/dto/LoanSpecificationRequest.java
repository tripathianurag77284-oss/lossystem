package com.los.salesservice.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoanSpecificationRequest {

    private String loanProduct;

    private Double loanAmount;

    private Integer loanTenure;

    private Double interestRate;
}