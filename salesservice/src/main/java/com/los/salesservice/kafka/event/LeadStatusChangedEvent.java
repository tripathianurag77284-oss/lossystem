package com.los.salesservice.kafka.event;


import com.los.salesservice.model.ApplicationStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeadStatusChangedEvent {

    private Long leadId;

    private ApplicationStatus oldStatus;

    private ApplicationStatus newStatus;

    private String remarks;
}