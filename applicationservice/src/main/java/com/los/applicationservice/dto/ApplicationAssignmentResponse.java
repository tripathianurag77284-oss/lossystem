package com.los.applicationservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationAssignmentResponse {

    private Long assignmentId;

    private Long applicationId;

    private String assignedForTaskId;

    private Long taskStageId;

    private String assignmentStatus;

    private String assignmentRemark;

    private Long proceedToUserId;

    private Boolean isTerminal;

    private BigDecimal totalProgress;

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

