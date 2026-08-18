package com.los.applicationservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeadAssignmentRequest {
    
    @NotBlank(message = "Assigned task is required")
    private String assignedForTaskId;

    @NotBlank(message = "Assignment status is required")
    private String assignmentStatus;

    private String assignmentRemark;

    private Long proceedToId;

    private Boolean isTerminal;
}