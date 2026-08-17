package com.los.salesservice.dto;

import com.los.salesservice.model.ApplicationStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeadTrackingResponse {

    private Long leadId;

    private ApplicationStatus status;

    private LocalDateTime timestamp;

    private String remarks;
}