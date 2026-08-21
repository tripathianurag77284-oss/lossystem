package com.los.applicationservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "application")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "application_id")
    private Long applicationId;

    @Column(name = "lead_id")
    private Long leadId;

    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "application_number")
    private String applicationNumber;

    @Column(name = "loan_product")
    private String loanProduct;

    @Column(name = "loan_amount")
    private Double loanAmount;

    @Column(name = "loan_tenure")
    private Integer loanTenure;

    @Column(name = "interest_rate")
    private Double interestRate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ApplicationStatus status;

    @Column(name = "description")
    private String description;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;
}