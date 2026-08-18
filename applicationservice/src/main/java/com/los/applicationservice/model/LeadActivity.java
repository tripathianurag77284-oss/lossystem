package com.los.applicationservice.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeadActivity {

    private Long activityId;

    private Long leadId;

    private String action;

    private ApplicationStatus oldStatus;

    private ApplicationStatus newStatus;

    private String remarks;

    private LocalDateTime createdAt;
}