package com.los.salesservice.dto;

import com.los.salesservice.model.ApplicationStatus;
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