package com.los.salesservice.kafka.event;

import com.los.salesservice.model.ApplicationStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationStatusChangedEvent {

    private Long applicationId;

    private Long leadId;

    private ApplicationStatus oldStatus;

    private ApplicationStatus newStatus;

    private LocalDateTime modifiedAt;
}