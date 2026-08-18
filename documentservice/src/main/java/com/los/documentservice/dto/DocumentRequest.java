package com.los.documentservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocumentRequest {

    @NotNull(message = "applicantId is required")
    private Long applicantId;

    @NotNull(message = "documentTypeId is required")
    private Long documentTypeId;

    @NotBlank(message = "uploadStage is required")
    private String uploadStage;

    @NotBlank(message = "fileUrl is required")
    private String fileUrl;

    @NotNull(message = "verifiedFlag is required")
    private Boolean verifiedFlag;
    private String verificationRemark;
    private String verificationStatus;
    private String verificationMode;
    @NotNull(message = "validatedFlag is required")
    private Boolean validatedFlag;
    private String validationRemark;
    private String validationStatus;
    private String validationMode;
    @NotNull(message = "isActive is required")
    private Boolean isActive;

    @NotNull(message = "createdBy is required")
    private Long createdBy;

    @NotNull(message = "modifiedBy is required")
    private Long modifiedBy;
    private Long verifiedBy;
    private Long validatedBy;
}
