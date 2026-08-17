package com.los.salesservice.mock;

import com.los.salesservice.model.ApplicationStatus;
import com.los.salesservice.model.Lead;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class LeadMockData {

    public static final List<Lead> leads = new ArrayList<>(List.of(

            new Lead(
                    1001L,
                    "WEBSITE",
                    "ONLINE",
                    "9876543210",
                    "ABCDE1234F",
                    LocalDate.of(1995, 5, 15),
                    ApplicationStatus.NEW,
                    "Customer interested in home loan",
                    true,
                    LocalDateTime.now(),
                    LocalDateTime.now(),
                    101L,
                    101L,
                    false
            ),

            new Lead(
                    1002L,
                    "BRANCH",
                    "OFFLINE",
                    "9876543211",
                    "FGHIJ5678K",
                    LocalDate.of(1990, 8, 20),
                    ApplicationStatus.CONTACTED,
                    "Customer contacted by sales team",
                    true,
                    LocalDateTime.now(),
                    LocalDateTime.now(),
                    102L,
                    102L,
                    false
            ),

            new Lead(
                    1003L,
                    "AGENT",
                    "PARTNER",
                    "9876543212",
                    "KLMNO9012P",
                    LocalDate.of(1988, 12, 10),
                    ApplicationStatus.QUALIFIED,
                    "Lead qualified by sales agent",
                    true,
                    LocalDateTime.now(),
                    LocalDateTime.now(),
                    103L,
                    103L,
                    false
            ),

            new Lead(
                    1004L,
                    "WEBSITE",
                    "ONLINE",
                    "9876543213",
                    "PQRST3456U",
                    LocalDate.of(1992, 3, 25),
                    ApplicationStatus.DOCUMENT_PENDING,
                    "Documents requested from customer",
                    true,
                    LocalDateTime.now(),
                    LocalDateTime.now(),
                    101L,
                    101L,
                    false
            )

    ));

    private LeadMockData() {
    }

    public static List<Lead> getLeads() {
        return leads;
    }
}