package com.los.applicationservice.kafka;

import com.los.applicationservice.kafka.event.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaConsumerService {

    @KafkaListener(
            topics = "lead-created",
            groupId = "application-service-group"
    )
    public void consumeLeadCreated(
            LeadCreatedEvent event) {

        log.info(
                "Lead created event received: {}",
                event
        );
    }

    @KafkaListener(
            topics = "lead-status-updated",
            groupId = "application-service-group"
    )
    public void consumeLeadStatusUpdated(
            LeadStatusUpdatedEvent event) {

        log.info(
                "Lead status updated event received: {}",
                event
        );
    }

    @KafkaListener(
            topics = "application-created",
            groupId = "application-service-group"
    )
    public void consumeApplicationCreated(
            ApplicationCreatedEvent event) {

        log.info(
                "Application created event received: {}",
                event
        );
    }

    @KafkaListener(
            topics = "application-status-changed",
            groupId = "application-service-group"
    )
    public void consumeApplicationStatusChanged(
            ApplicationStatusChangedEvent event) {

        log.info(
                "Application status changed event received: {}",
                event
        );
    }

    @KafkaListener(
            topics = "lead-assignment-created",
            groupId = "application-service-group"
    )
    public void consumeLeadAssignmentCreated(
            LeadAssignmentCreatedEvent event) {

        log.info(
                "Lead assignment created event received: {}",
                event
        );
    }

    @KafkaListener(
            topics = "lead-assignment-status-updated",
            groupId = "application-service-group"
    )
    public void consumeLeadAssignmentStatusUpdated(
            LeadAssignmentStatusUpdatedEvent event) {

        log.info(
                "Lead assignment status updated event received: {}",
                event
        );
    }

    @KafkaListener(
            topics = "application-assignment-created",
            groupId = "application-service-group"
    )
    public void consumeApplicationAssignmentCreated(
            ApplicationAssignmentCreatedEvent event) {

        log.info(
                "Application assignment created event received: {}",
                event
        );
    }

    @KafkaListener(
            topics = "application-assignment-status-changed",
            groupId = "application-service-group"
    )
    public void consumeApplicationAssignmentStatusChanged(
            ApplicationAssignmentStatusChangedEvent event) {

        log.info(
                "Application assignment status changed event received: {}",
                event
        );
    }
}