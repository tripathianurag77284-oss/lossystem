package com.los.applicationservice.kafka.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationAssignmentStatusChangedEvent {

    private Long assignmentId;

    private Long applicationId;

    private String oldStatus;

    private String newStatus;

    private LocalDateTime modifiedAt;
}