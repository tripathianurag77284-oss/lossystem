package com.los.applicationservice.kafka.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeadAssignmentCreatedEvent {

    private Long assignmentId;
    private Long leadId;
    private String assignedForTaskId;
    private String assignmentStatus;
    private Long proceedToId;
    private Boolean isTerminal;
    private LocalDateTime createdAt;
}