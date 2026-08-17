package com.los.salesservice.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Application {

    private Long applicationId;

    private Long leadId;

    private Long customerId;

    private String applicationNumber;

    private String loanProduct;

    private Double loanAmount;

    private Integer loanTenure;

    private Double interestRate;

    private ApplicationStatus status;

    private String description;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;
}