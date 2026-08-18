package com.los.applicationservice.dto;

import com.los.applicationservice.model.ApplicationStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeadRequest {

    @NotBlank(message = "Source name is required")
    private String sourceName;

    @NotBlank(message = "Channel type is required")
    private String channelType;

    @NotBlank(message = "Mobile number is required")
    @Pattern(
            regexp = "^[6-9][0-9]{9}$",
            message = "Invalid mobile number"
    )
    private String mobile;

    @NotBlank(message = "PAN is required")
    @Pattern(
            regexp = "^[A-Z]{5}[0-9]{4}[A-Z]{1}$",
            message = "Invalid PAN format"
    )
    private String pan;

    @NotNull(message = "Date of birth is required")
    private LocalDate dob;

    private ApplicationStatus status;

    private String description;
}

