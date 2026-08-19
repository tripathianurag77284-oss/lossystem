package com.los.applicationservice.kafka;

import com.los.applicationservice.kafka.event.ApplicationAssignmentCreatedEvent;
import com.los.applicationservice.kafka.event.ApplicationAssignmentStatusChangedEvent;
import com.los.applicationservice.kafka.event.ApplicationCreatedEvent;
import com.los.applicationservice.kafka.event.ApplicationStatusChangedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducerService {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    // =========================================================
    // TOPICS
    // =========================================================

    private static final String LEAD_CREATED_TOPIC =
            "lead-created";

    private static final String LEAD_STATUS_UPDATED_TOPIC =
            "lead-status-updated";

    private static final String APPLICATION_CREATED_TOPIC =
            "application-created";

    private static final String APPLICATION_STATUS_CHANGED_TOPIC =
            "application-status-changed";

    private static final String LEAD_ASSIGNMENT_CREATED_TOPIC =
            "lead-assignment-created";

    private static final String LEAD_ASSIGNMENT_STATUS_UPDATED_TOPIC =
            "lead-assignment-status-updated";

    private static final String APPLICATION_ASSIGNMENT_CREATED_TOPIC =
            "application-assignment-created";

    private static final String APPLICATION_ASSIGNMENT_STATUS_CHANGED_TOPIC =
            "application-assignment-status-changed";


    // =========================================================
    // LEAD CREATED
    // =========================================================

    public void sendLeadCreated(
            Long leadId,
            Object event) {

        kafkaTemplate.send(
                LEAD_CREATED_TOPIC,
                String.valueOf(leadId),
                event
        );
    }


    // =========================================================
    // LEAD STATUS UPDATED
    // =========================================================

    public void sendLeadStatusUpdated(
            Long leadId,
            Object event) {

        kafkaTemplate.send(
                LEAD_STATUS_UPDATED_TOPIC,
                String.valueOf(leadId),
                event
        );
    }


    // =========================================================
    // APPLICATION CREATED
    // =========================================================

    public void sendApplicationCreatedEvent(
            ApplicationCreatedEvent event) {

        kafkaTemplate.send(
                APPLICATION_CREATED_TOPIC,
                String.valueOf(event.getApplicationId()),
                event
        );
    }


    // =========================================================
    // APPLICATION STATUS CHANGED
    // =========================================================

    public void sendApplicationStatusChangedEvent(
            ApplicationStatusChangedEvent event) {

        kafkaTemplate.send(
                APPLICATION_STATUS_CHANGED_TOPIC,
                String.valueOf(event.getApplicationId()),
                event
        );
    }


    // =========================================================
    // LEAD ASSIGNMENT CREATED
    // =========================================================

    public void sendLeadAssignmentCreated(
            Long assignmentId,
            Object event) {

        kafkaTemplate.send(
                LEAD_ASSIGNMENT_CREATED_TOPIC,
                String.valueOf(assignmentId),
                event
        );
    }


    // =========================================================
    // LEAD ASSIGNMENT STATUS UPDATED
    // =========================================================

    public void sendLeadAssignmentStatusUpdated(
            Long assignmentId,
            Object event) {

        kafkaTemplate.send(
                LEAD_ASSIGNMENT_STATUS_UPDATED_TOPIC,
                String.valueOf(assignmentId),
                event
        );
    }


    // =========================================================
    // APPLICATION ASSIGNMENT CREATED
    // =========================================================

    public void sendApplicationAssignmentCreated(
            ApplicationAssignmentCreatedEvent event) {

        kafkaTemplate.send(
                APPLICATION_ASSIGNMENT_CREATED_TOPIC,
                String.valueOf(event.getAssignmentId()),
                event
        );
    }


    // =========================================================
    // APPLICATION ASSIGNMENT STATUS CHANGED
    // =========================================================

    public void sendApplicationAssignmentStatusChanged(
            ApplicationAssignmentStatusChangedEvent event) {

        kafkaTemplate.send(
                APPLICATION_ASSIGNMENT_STATUS_CHANGED_TOPIC,
                String.valueOf(event.getAssignmentId()),
                event
        );
    }

}