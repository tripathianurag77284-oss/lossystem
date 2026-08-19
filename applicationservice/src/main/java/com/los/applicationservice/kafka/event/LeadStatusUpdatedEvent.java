package com.los.applicationservice.kafka.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeadStatusUpdatedEvent {

    private Long leadId;
    private String status;
    private LocalDateTime modifiedAt;
}