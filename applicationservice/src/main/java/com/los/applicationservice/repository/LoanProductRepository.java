package com.los.applicationservice.repository;

import com.los.applicationservice.model.LoanProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanProductRepository
        extends JpaRepository<LoanProduct, Long> {
}