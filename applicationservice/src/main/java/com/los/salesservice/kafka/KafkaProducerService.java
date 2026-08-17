package com.los.salesservice.kafka;


import com.los.salesservice.kafka.event.ApplicationCreatedEvent;
import com.los.salesservice.kafka.event.ApplicationStatusChangedEvent;
import com.los.salesservice.kafka.event.LeadCreatedEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public KafkaProducerService(
            KafkaTemplate<String, Object> kafkaTemplate) {

        this.kafkaTemplate = kafkaTemplate;
    }

    // =========================================================
    // LEAD CREATED
    // =========================================================

    public void sendLeadCreatedEvent(
            LeadCreatedEvent event) {

        kafkaTemplate.send(
                "lead-created",
                event.getLeadId().toString(),
                event
        );
    }

    // =========================================================
    // APPLICATION CREATED
    // =========================================================

    public void sendApplicationCreatedEvent(
            ApplicationCreatedEvent event) {

        kafkaTemplate.send(
                "application-created",
                event.getApplicationId().toString(),
                event
        );
    }

    // =========================================================
    // APPLICATION STATUS CHANGED
    // =========================================================

    public void sendApplicationStatusChangedEvent(
            ApplicationStatusChangedEvent event) {

        kafkaTemplate.send(
                "application-status-changed",
                event.getApplicationId().toString(),
                event
        );
    }
}