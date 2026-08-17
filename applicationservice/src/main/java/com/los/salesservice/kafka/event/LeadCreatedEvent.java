package com.los.salesservice.kafka.event;

import com.los.salesservice.model.ApplicationStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeadCreatedEvent {

    private Long leadId;

    private String sourceName;

    private String channelType;

    private String mobile;

    private String pan;

    private LocalDate dob;

    private ApplicationStatus status;

    private String description;
}