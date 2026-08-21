package com.los.customerservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicantRequest {

    private Long applicationId;
    private String cifNo;

    private Long applicationRoleId;
    private Long primaryApplicantId;
    private Long relationshipTypeId;
    private Long partyId;

    private Double share;
}