package com.los.customerservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApplicantResponse {

    private Long applicantId;

    private Long applicationId;
    private String cifNo;

    private Long applicationRoleId;
    private Long primaryApplicantId;
    private Long relationshipTypeId;
    private Long partyId;

    private Double share;

    private Boolean verifiedFlag;
    private String verificationRemark;
    private String verificationStatus;
    private String verificationMode;

    private Boolean validatedFlag;
    private String validationRemark;
    private String validationStatus;
    private String validationMode;

    private Boolean isActive;
}