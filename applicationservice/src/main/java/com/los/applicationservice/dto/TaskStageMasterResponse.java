package com.los.applicationservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskStageMasterResponse {

    private Long taskStageId;

    private String stageName;

    private BigDecimal progress;

    private LocalDateTime deadline;

    private LocalDateTime expectedSubmission;

    private Boolean isActive;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;

    private Long createdById;

    private Long modifiedById;

    private Boolean isDeleted;
}

