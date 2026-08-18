package com.los.applicationservice.mock;

import com.los.applicationservice.model.LoanProduct;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class LoanProductMockData {

    public static List<LoanProduct> getLoanProducts() {

        LocalDateTime now = LocalDateTime.now();

        return List.of(

                new LoanProduct(
                        2001L,
                        "Home Loan",
                        "HOME_LOAN_STANDARD",
                        "HL001",
                        201L,
                        "HOME_LOAN",
                        new BigDecimal("500000"),
                        new BigDecimal("10000000"),
                        12,
                        360,
                        true,
                        now,
                        now,
                        103L,
                        103L,
                        false
                ),

                new LoanProduct(
                        2002L,
                        "Personal Loan",
                        "PERSONAL_LOAN_STANDARD",
                        "PL001",
                        202L,
                        "PERSONAL_LOAN",
                        new BigDecimal("50000"),
                        new BigDecimal("2000000"),
                        6,
                        84,
                        true,
                        now,
                        now,
                        103L,
                        103L,
                        false
                ),

                new LoanProduct(
                        2003L,
                        "Vehicle Loan",
                        "VEHICLE_LOAN_STANDARD",
                        "VL001",
                        203L,
                        "VEHICLE_LOAN",
                        new BigDecimal("100000"),
                        new BigDecimal("5000000"),
                        12,
                        84,
                        true,
                        now,
                        now,
                        103L,
                        103L,
                        false
                )
        );
    }

    private LoanProductMockData() {
    }
}