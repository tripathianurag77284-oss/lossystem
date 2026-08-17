package com.los.salesservice.mock;

import com.los.salesservice.dto.LoanProductResponse;

import java.util.List;

public class LoanProductMockData {

    private LoanProductMockData() {
    }

    public static List<LoanProductResponse> getLoanProducts() {

        return List.of(

                new LoanProductResponse(
                        "HOME_LOAN",
                        "Home Loan",
                        500000.0,
                        10000000.0,
                        60,
                        240,
                        8.5
                ),

                new LoanProductResponse(
                        "PERSONAL_LOAN",
                        "Personal Loan",
                        50000.0,
                        2000000.0,
                        12,
                        84,
                        11.5
                ),

                new LoanProductResponse(
                        "CAR_LOAN",
                        "Car Loan",
                        100000.0,
                        5000000.0,
                        12,
                        84,
                        9.5
                )
        );
    }
}