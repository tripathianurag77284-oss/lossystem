package com.los.applicationservice.kafka.event;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeadAssignmentStatusUpdatedEvent {

    private Long assignmentId;
    private String assignmentStatus;
    private LocalDateTime modifiedAt;
}