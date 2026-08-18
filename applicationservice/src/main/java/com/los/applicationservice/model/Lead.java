package com.los.applicationservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Lead {

    private Long leadId;

    private String sourceName;

    private String channelType;

    private String mobile;

    private String pan;

    private LocalDate dob;

    private ApplicationStatus status;

    private String description;

    private Boolean isActive;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;

    private Long createdById;

    private Long modifiedById;

    private Boolean isDeleted;
}