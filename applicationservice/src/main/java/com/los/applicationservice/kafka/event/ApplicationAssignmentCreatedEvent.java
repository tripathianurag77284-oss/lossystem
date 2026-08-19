package com.los.applicationservice.kafka.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationAssignmentCreatedEvent {

    private Long assignmentId;

    private Long applicationId;

    private String assignedForTaskId;

    private Long taskStageId;

    private String assignmentStatus;

    private Long proceedToUserId;

    private LocalDateTime createdAt;
}