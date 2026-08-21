package com.los.applicationservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "task_stage_master")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskStageMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_stage_id")
    private Long taskStageId;

    @Column(name = "stage_name")
    private String stageName;

    @Column(name = "progress", precision = 19, scale = 4)
    private BigDecimal progress;

    @Column(name = "deadline")
    private LocalDateTime deadline;

    @Column(name = "expected_submission")
    private LocalDateTime expectedSubmission;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;

    @Column(name = "created_by_id")
    private Long createdById;

    @Column(name = "modified_by_id")
    private Long modifiedById;

    @Column(name = "is_deleted")
    private Boolean isDeleted;
}