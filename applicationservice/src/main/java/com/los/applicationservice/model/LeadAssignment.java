package com.los.applicationservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "lead_assignment")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LeadAssignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "assignment_id")
    private Long assignmentId;

    @Column(name = "lead_id")
    private Long leadId;

    @Column(name = "assigned_for_task_id")
    private String assignedForTaskId;

    @Column(name = "assignment_status")
    private String assignmentStatus;

    @Column(name = "assignment_remark")
    private String assignmentRemark;

    @Column(name = "proceed_to_id")
    private Long proceedToId;

    @Column(name = "is_terminal")
    private Boolean isTerminal;

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