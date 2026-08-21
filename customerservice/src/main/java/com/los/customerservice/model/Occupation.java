package com.los.customerservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "occupation")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Occupation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "occupation_id")
    private Long occupationId;

    @Column(name = "occupation_name")
    private String occupationName;

    @Column(name = "occupation_category")
    private String occupationCategory;

    @Column(name = "is_active")
    private Boolean isActive;
}