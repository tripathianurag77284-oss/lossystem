package com.los.documentservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Document {

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
}
