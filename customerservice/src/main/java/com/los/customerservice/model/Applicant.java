package com.los.customerservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "applicant")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Applicant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "applicant_id")
    private Long applicantId;

    @Column(name = "application_id")
    private Long applicationId;

    @Column(name = "cif_no")
    private String cifNo;

    @Column(name = "application_role_id")
    private Long applicationRoleId;

    @Column(name = "primary_applicant_id")
    private Long primaryApplicantId;

    @Column(name = "relationship_type_id")
    private Long relationshipTypeId;

    @Column(name = "party_id")
    private Long partyId;

    private Double share;

    @Column(name = "verified_flag")
    private Boolean verifiedFlag;

    @Column(name = "verification_remark")
    private String verificationRemark;

    @Column(name = "verification_status")
    private String verificationStatus;

    @Column(name = "verification_mode")
    private String verificationMode;

    @Column(name = "validated_flag")
    private Boolean validatedFlag;

    @Column(name = "validation_remark")
    private String validationRemark;

    @Column(name = "validation_status")
    private String validationStatus;

    @Column(name = "validation_mode")
    private String validationMode;

    @Column(name = "is_active")
    private Boolean isActive;
}