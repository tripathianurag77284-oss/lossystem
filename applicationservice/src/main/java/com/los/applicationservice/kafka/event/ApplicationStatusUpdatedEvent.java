package com.los.applicationservice.kafka.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationStatusUpdatedEvent {

    private Long applicationId;
    private String status;
    private String currentStage;
    private LocalDateTime modifiedAt;
}