package com.los.applicationservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "application_assignment")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationAssignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "assignment_id")
    private Long assignmentId;

    @Column(name = "application_id")
    private Long applicationId;

    @Column(name = "assigned_for_task_id")
    private String assignedForTaskId;

    @Column(name = "task_stage_id")
    private Long taskStageId;

    @Column(name = "assignment_status")
    private String assignmentStatus;

    @Column(name = "assignment_remark")
    private String assignmentRemark;

    @Column(name = "proceed_to_user_id")
    private Long proceedToUserId;

    @Column(name = "is_terminal")
    private Boolean isTerminal;

    @Column(name = "total_progress", precision = 19, scale = 4)
    private BigDecimal totalProgress;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;

    @Column(name = "verified_at")
    private LocalDateTime verifiedAt;

    @Column(name = "created_by_id")
    private Long createdById;

    @Column(name = "modified_by_id")
    private Long modifiedById;

    @Column(name = "verified_by_id")
    private Long verifiedById;

    @Column(name = "verification_mode")
    private String verificationMode;

    @Column(name = "is_deleted")
    private Boolean isDeleted;
}