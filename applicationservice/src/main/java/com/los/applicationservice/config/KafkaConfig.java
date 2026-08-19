package com.los.applicationservice.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic leadCreatedTopic() {
        return TopicBuilder
                .name("lead-created")
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic leadStatusUpdatedTopic() {
        return TopicBuilder
                .name("lead-status-updated")
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic applicationCreatedTopic() {
        return TopicBuilder
                .name("application-created")
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic applicationStatusUpdatedTopic() {
        return TopicBuilder
                .name("application-status-updated")
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic leadAssignmentCreatedTopic() {
        return TopicBuilder
                .name("lead-assignment-created")
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic leadAssignmentStatusUpdatedTopic() {
        return TopicBuilder
                .name("lead-assignment-status-updated")
                .partitions(1)
                .replicas(1)
                .build();
    }
}