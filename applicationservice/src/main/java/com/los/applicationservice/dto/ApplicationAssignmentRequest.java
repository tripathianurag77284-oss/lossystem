package com.los.applicationservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationAssignmentRequest {

    @NotBlank(message = "Assigned task is required")
    private String assignedForTaskId;

    @NotNull(message = "Task stage is required")
    private Long taskStageId;

    private String assignmentStatus;

    private String assignmentRemark;

    private Long proceedToUserId;

    private Boolean isTerminal;
}

