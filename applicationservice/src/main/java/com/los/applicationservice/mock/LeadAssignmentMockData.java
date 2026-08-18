package com.los.applicationservice.mock;

import com.los.applicationservice.model.LeadAssignment;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class LeadAssignmentMockData {

    public static final List<LeadAssignment> assignments =
            new ArrayList<>(List.of(

                    new LeadAssignment(
                            5001L,
                            1001L,
                            "LEAD_VERIFICATION",
                            "ASSIGNED",
                            "Lead assigned for initial verification",
                            101L,
                            false,
                            true,
                            LocalDateTime.now(),
                            LocalDateTime.now(),
                            null,
                            101L,
                            101L,
                            null,
                            "SYSTEM",
                            false
                    ),

                    new LeadAssignment(
                            5002L,
                            1002L,
                            "CUSTOMER_CONTACT",
                            "IN_PROGRESS",
                            "Sales officer is contacting customer",
                            102L,
                            false,
                            true,
                            LocalDateTime.now(),
                            LocalDateTime.now(),
                            null,
                            102L,
                            102L,
                            null,
                            "USER",
                            false
                    ),

                    new LeadAssignment(
                            5003L,
                            1003L,
                            "DOCUMENT_COLLECTION",
                            "COMPLETED",
                            "Required documents collected",
                            103L,
                            true,
                            true,
                            LocalDateTime.now(),
                            LocalDateTime.now(),
                            LocalDateTime.now(),
                            103L,
                            103L,
                            103L,
                            "USER",
                            false
                    ),

                    new LeadAssignment(
                            5004L,
                            1004L,
                            "LEAD_VERIFICATION",
                            "PENDING",
                            "Waiting for verification",
                            101L,
                            false,
                            true,
                            LocalDateTime.now(),
                            LocalDateTime.now(),
                            null,
                            101L,
                            101L,
                            null,
                            "SYSTEM",
                            false
                    )
            ));

    private LeadAssignmentMockData() {
    }

    public static List<LeadAssignment> getAssignments() {
        return assignments;
    }
}