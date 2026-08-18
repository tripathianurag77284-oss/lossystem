package com.los.applicationservice.dto;

import com.los.applicationservice.model.ApplicationStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationStatusUpdateRequest  {

    @NotNull
    private ApplicationStatus status;
}