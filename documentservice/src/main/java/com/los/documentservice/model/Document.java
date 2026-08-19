package com.los.documentservice.model;

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
    private Long documentId;
    private Long applicantId;
    private Long documentTypeId;
    private String uploadStage;
    private String fileUrl;
    private Boolean verifiedFlag;
    private String verificationRemark;
    private String verificationStatus;
    private String verificationMode;
    private Boolean validatedFlag;
    private String validationRemark;
    private String validationStatus;
    private String validationMode;
    private Boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private LocalDateTime verifiedAt;
    private LocalDateTime validatedAt;
    private Long createdBy;
    private Long modifiedBy;
    private Long verifiedBy;
    private Long validatedBy;
    private Boolean isDeleted;
    private String verificationMethod;
}
