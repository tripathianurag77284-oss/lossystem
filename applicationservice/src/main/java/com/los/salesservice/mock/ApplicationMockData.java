package com.los.salesservice.mock;

import com.los.salesservice.model.Application;
import com.los.salesservice.model.ApplicationStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ApplicationMockData {

    public static final List<Application> applications =
            new ArrayList<>(List.of(

                    new Application(
                            5001L,
                            1001L,
                            2001L,
                            "APP-5001",
                            "HOME_LOAN",
                            5000000.0,
                            240,
                            8.5,
                            ApplicationStatus.SUBMITTED,
                            "Home loan application",
                            LocalDateTime.now(),
                            LocalDateTime.now()
                    ),

                    new Application(
                            5002L,
                            1002L,
                            2002L,
                            "APP-5002",
                            "PERSONAL_LOAN",
                            500000.0,
                            60,
                            11.5,
                            ApplicationStatus.DOCUMENT_PENDING,
                            "Personal loan application",
                            LocalDateTime.now(),
                            LocalDateTime.now()
                    ),

                    new Application(
                            5003L,
                            1003L,
                            2003L,
                            "APP-5003",
                            "CAR_LOAN",
                            1000000.0,
                            84,
                            9.5,
                            ApplicationStatus.CREDIT_ASSESSMENT,
                            "Car loan application",
                            LocalDateTime.now(),
                            LocalDateTime.now()
                    ),
                    new Application(
                            5004L,
                            1004L,
                            2004L,
                            "APP-5004",
                            "PERSONAL_LOAN",
                            300000.0,
                            48,
                            10.5,
                            ApplicationStatus.DRAFT,
                            "Draft personal loan application",
                            LocalDateTime.now(),
                            LocalDateTime.now()
                    )
            ));

    private ApplicationMockData() {
    }

    public static List<Application> getApplications() {
        return applications;
    }
}