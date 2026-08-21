package com.los.documentservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "documents")
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "document_id")
    private Long documentId;
    @Column(name = "applicant_id", nullable = false)
    private Long applicantId;
    @Column(name = "document_type_id", nullable = false)
    private Long documentTypeId;
    @Column(name = "upload_stage", nullable = false, length = 100)
    private String uploadStage;
    @Column(name = "file_url", length = 1000)
    private String fileUrl;
    @Column(name = "verified_flag", nullable = false)
    private Boolean verifiedFlag;
    @Column(name = "verification_remark", length = 1000)
    private String verificationRemark;
    @Column(name = "verification_status", length = 100)
    private String verificationStatus;
    @Column(name = "verification_mode", length = 100)
    private String verificationMode;
    @Column(name = "validated_flag", nullable = false)
    private Boolean validatedFlag;
    @Column(name = "validation_remark", length = 1000)
    private String validationRemark;
    @Column(name = "validation_status", length = 100)
    private String validationStatus;
    @Column(name = "validation_mode", length = 100)
    private String validationMode;
    @Column(name = "is_active", nullable = false)
    private Boolean isActive;
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    @Column(name = "modified_at", nullable = false)
    private LocalDateTime modifiedAt;
    @Column(name = "verified_at")
    private LocalDateTime verifiedAt;
    @Column(name = "validated_at")
    private LocalDateTime validatedAt;
    @Column(name = "created_by", nullable = false)
    private Long createdBy;
    @Column(name = "modified_by", nullable = false)
    private Long modifiedBy;
    @Column(name = "verified_by")
    private Long verifiedBy;
    @Column(name = "validated_by")
    private Long validatedBy;
    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted;
    @Column(name = "verification_method", length = 100)
    private String verificationMethod;
}
