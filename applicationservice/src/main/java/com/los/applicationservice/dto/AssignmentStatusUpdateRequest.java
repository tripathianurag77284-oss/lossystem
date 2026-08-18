package com.los.applicationservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssignmentStatusUpdateRequest {

    @NotBlank(message = "Assignment status is required")
    private String status;

    private String remark;
}

