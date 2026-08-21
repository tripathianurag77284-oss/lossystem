package com.los.customerservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PartyResponse {

    private Long partyId;

    private String firstName;
    private String middleName;
    private String lastName;

    private String fatherName;
    private String spouseName;

    private String mobile;
    private Boolean isMobileVerified;

    private String alternateMobile;
    private Boolean isAlternateMobileVerified;

    private String email;
    private Boolean isEmailVerified;

    private LocalDate dob;
    private Boolean isDobVerified;

    private String pan;
    private Boolean isPanVerified;

    private String aadhar;
    private Boolean isAadharVerified;

    private Long occupationId;
    private String otherOccupation;

    private String maritalStatus;
    private String numberOfDependent;

    private String educationType;
    private Integer education;

    private Long eduDocId;
    private Long poiDocId;
    private Long poaDocId;
    private Long photoDocId;

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