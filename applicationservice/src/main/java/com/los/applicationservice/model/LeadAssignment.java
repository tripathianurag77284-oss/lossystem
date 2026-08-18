package com.los.applicationservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LeadAssignment {

    private Long assignmentId;

    private Long leadId;

    private String assignedForTaskId;

    private String assignmentStatus;

    private String assignmentRemark;

    private Long proceedToId;

    private Boolean isTerminal;

    private Boolean isActive;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;

    private LocalDateTime verifiedAt;

    private Long createdById;

    private Long modifiedById;

    private Long verifiedById;

    private String verificationMode;

    private Boolean isDeleted;
}