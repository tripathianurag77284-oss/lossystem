package com.los.customerservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "party")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Party {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "party_id")
    private Long partyId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "father_name")
    private String fatherName;

    @Column(name = "spouse_name")
    private String spouseName;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "is_mobile_verified")
    private Boolean isMobileVerified;

    @Column(name = "alternate_mobile")
    private String alternateMobile;

    @Column(name = "is_alternate_mobile_verified")
    private Boolean isAlternateMobileVerified;

    @Column(name = "email")
    private String email;

    @Column(name = "is_email_verified")
    private Boolean isEmailVerified;

    @Column(name = "dob")
    private LocalDate dob;

    @Column(name = "is_dob_verified")
    private Boolean isDobVerified;

    @Column(name = "pan")
    private String pan;

    @Column(name = "is_pan_verified")
    private Boolean isPanVerified;

    @Column(name = "aadhar")
    private String aadhar;

    @Column(name = "is_aadhar_verified")
    private Boolean isAadharVerified;

    @Column(name = "occupation_id")
    private Long occupationId;

    @Column(name = "other_occupation")
    private String otherOccupation;

    @Column(name = "marital_status")
    private String maritalStatus;

    @Column(name = "number_of_dependent")
    private String numberOfDependent;

    @Column(name = "education_type")
    private String educationType;

    @Column(name = "education")
    private Integer education;

    @Column(name = "edu_doc_id")
    private Long eduDocId;

    @Column(name = "poi_doc_id")
    private Long poiDocId;

    @Column(name = "poa_doc_id")
    private Long poaDocId;

    @Column(name = "photo_doc_id")
    private Long photoDocId;

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