package com.los.applicationservice.mock;

import com.los.applicationservice.model.ApplicationAssignment;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ApplicationAssignmentMockData {

    private static final List<ApplicationAssignment> assignments =
            new ArrayList<>(List.of(

                    new ApplicationAssignment(
                            5001L,
                            10001L,
                            "KYC_VERIFICATION",
                            101L,
                            "ASSIGNED",
                            "KYC verification assigned to user",
                            201L,
                            false,
                            BigDecimal.valueOf(25),
                            true,
                            LocalDateTime.now(),
                            LocalDateTime.now(),
                            null,
                            101L,
                            101L,
                            null,
                            null,
                            false
                    ),

                    new ApplicationAssignment(
                            5002L,
                            10001L,
                            "INCOME_VERIFICATION",
                            103L,
                            "IN_PROGRESS",
                            "Income verification in progress",
                            202L,
                            false,
                            BigDecimal.valueOf(50),
                            true,
                            LocalDateTime.now(),
                            LocalDateTime.now(),
                            null,
                            101L,
                            202L,
                            null,
                            null,
                            false
                    ),

                    new ApplicationAssignment(
                            5003L,
                            10002L,
                            "CREDIT_CHECK",
                            104L,
                            "COMPLETED",
                            "Credit assessment completed",
                            203L,
                            true,
                            BigDecimal.valueOf(75),
                            true,
                            LocalDateTime.now(),
                            LocalDateTime.now(),
                            LocalDateTime.now(),
                            102L,
                            203L,
                            203L,
                            "SYSTEM",
                            false
                    )
            ));

    private ApplicationAssignmentMockData() {
    }

    public static List<ApplicationAssignment> getAssignments() {
        return assignments;
    }
}

