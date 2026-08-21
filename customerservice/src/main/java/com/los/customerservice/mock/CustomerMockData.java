package com.los.customerservice.mock;


import com.los.customerservice.model.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class CustomerMockData {

    public static final Map<Long, Occupation> OCCUPATIONS =
            new HashMap<>();

    public static final Map<Long, DocumentType> DOCUMENT_TYPES =
            new HashMap<>();

    public static final Map<Long, ApplicationRole> APPLICATION_ROLES =
            new HashMap<>();

    public static final Map<Long, RelationshipType> RELATIONSHIP_TYPES =
            new HashMap<>();

    public static final Map<Long, Party> PARTIES =
            new HashMap<>();

    public static final Map<Long, Applicant> APPLICANTS =
            new HashMap<>();


    static {

        // ==========================================
        // OCCUPATION MOCK DATA
        // ==========================================

        OCCUPATIONS.put(
                1L,
                new Occupation(
                        1L,
                        "Software Engineer",
                        "IT",
                        true
                )
        );

        OCCUPATIONS.put(
                2L,
                new Occupation(
                        2L,
                        "Doctor",
                        "Healthcare",
                        true
                )
        );

        OCCUPATIONS.put(
                3L,
                new Occupation(
                        3L,
                        "Teacher",
                        "Education",
                        true
                )
        );

        OCCUPATIONS.put(
                4L,
                new Occupation(
                        4L,
                        "Business Owner",
                        "Business",
                        true
                )
        );


        // ==========================================
        // DOCUMENT TYPE MOCK DATA
        // ==========================================

        DOCUMENT_TYPES.put(
                1L,
                new DocumentType(
                        1L,
                        "PAN Card",
                        "Identity",
                        true,
                        false,
                        false,
                        true
                )
        );

        DOCUMENT_TYPES.put(
                2L,
                new DocumentType(
                        2L,
                        "Aadhaar Card",
                        "Identity",
                        true,
                        true,
                        true,
                        true
                )
        );

        DOCUMENT_TYPES.put(
                3L,
                new DocumentType(
                        3L,
                        "Passport",
                        "Identity",
                        true,
                        true,
                        true,
                        true
                )
        );

        DOCUMENT_TYPES.put(
                4L,
                new DocumentType(
                        4L,
                        "Driving License",
                        "Identity",
                        true,
                        true,
                        true,
                        true
                )
        );


        // ==========================================
        // APPLICATION ROLE MOCK DATA
        // ==========================================

        APPLICATION_ROLES.put(
                1L,
                new ApplicationRole(
                        1L,
                        "APPLICANT",
                        "Primary applicant"
                )
        );

        APPLICATION_ROLES.put(
                2L,
                new ApplicationRole(
                        2L,
                        "CO_APPLICANT",
                        "Co applicant"
                )
        );

        APPLICATION_ROLES.put(
                3L,
                new ApplicationRole(
                        3L,
                        "GUARANTOR",
                        "Loan guarantor"
                )
        );

        APPLICATION_ROLES.put(
                4L,
                new ApplicationRole(
                        4L,
                        "NOMINEE",
                        "Nominee"
                )
        );


        // ==========================================
        // RELATIONSHIP TYPE MOCK DATA
        // ==========================================

        RELATIONSHIP_TYPES.put(
                1L,
                new RelationshipType(
                        1L,
                        "FATHER",
                        "Father"
                )
        );

        RELATIONSHIP_TYPES.put(
                2L,
                new RelationshipType(
                        2L,
                        "MOTHER",
                        "Mother"
                )
        );

        RELATIONSHIP_TYPES.put(
                3L,
                new RelationshipType(
                        3L,
                        "SPOUSE",
                        "Spouse"
                )
        );

        RELATIONSHIP_TYPES.put(
                4L,
                new RelationshipType(
                        4L,
                        "BROTHER",
                        "Brother"
                )
        );


        // ==========================================
        // PARTY MOCK DATA
        // ==========================================

        Party party1 = new Party();

        party1.setPartyId(1L);
        party1.setFirstName("Anurag");
        party1.setMiddleName("Kumar");
        party1.setLastName("Tripathi");
        party1.setFatherName("Rajesh Tripathi");

        party1.setMobile("9876543210");
        party1.setIsMobileVerified(true);

        party1.setEmail("anurag@example.com");
        party1.setIsEmailVerified(true);

        party1.setDob(
                LocalDate.of(1992, 5, 15)
        );

        party1.setIsDobVerified(true);

        party1.setPan("ABCDE1234F");
        party1.setIsPanVerified(true);

        party1.setAadhar("123456789012");
        party1.setIsAadharVerified(true);

        party1.setOccupationId(1L);

        party1.setMaritalStatus("MARRIED");
        party1.setNumberOfDependent("2");

        party1.setEducationType("GRADUATION");
        party1.setEducation(15);

        party1.setVerifiedFlag(true);
        party1.setVerificationStatus("VERIFIED");
        party1.setVerificationMode("ONLINE");

        party1.setValidatedFlag(true);
        party1.setValidationStatus("VALIDATED");
        party1.setValidationMode("SYSTEM");

        party1.setIsActive(true);

        PARTIES.put(1L, party1);


        // ==========================================
        // PARTY 2
        // ==========================================

        Party party2 = new Party();

        party2.setPartyId(2L);
        party2.setFirstName("Priya");
        party2.setMiddleName("Raj");
        party2.setLastName("Sharma");
        party2.setFatherName("Mahesh Sharma");

        party2.setMobile("9876501234");
        party2.setIsMobileVerified(true);

        party2.setEmail("priya@example.com");
        party2.setIsEmailVerified(true);

        party2.setDob(
                LocalDate.of(1995, 8, 20)
        );

        party2.setIsDobVerified(true);

        party2.setPan("PQRSX5678L");
        party2.setIsPanVerified(true);

        party2.setAadhar("234567890123");
        party2.setIsAadharVerified(true);

        party2.setOccupationId(3L);

        party2.setMaritalStatus("SINGLE");
        party2.setNumberOfDependent("1");

        party2.setEducationType("POST_GRADUATION");
        party2.setEducation(18);

        party2.setVerifiedFlag(true);
        party2.setVerificationStatus("VERIFIED");
        party2.setVerificationMode("ONLINE");

        party2.setValidatedFlag(true);
        party2.setValidationStatus("VALIDATED");
        party2.setValidationMode("SYSTEM");

        party2.setIsActive(true);

        PARTIES.put(2L, party2);


        // ==========================================
        // APPLICANT MOCK DATA
        // ==========================================

        Applicant applicant1 = new Applicant();

        applicant1.setApplicantId(1L);
        applicant1.setApplicationId(1001L);
        applicant1.setCifNo("CIF1000001");

        applicant1.setApplicationRoleId(1L);
        applicant1.setPartyId(1L);

        applicant1.setShare(100.0);

        applicant1.setVerifiedFlag(true);
        applicant1.setVerificationStatus("VERIFIED");
        applicant1.setVerificationMode("ONLINE");

        applicant1.setValidatedFlag(true);
        applicant1.setValidationStatus("VALIDATED");
        applicant1.setValidationMode("SYSTEM");

        applicant1.setIsActive(true);

        APPLICANTS.put(1L, applicant1);


        // ==========================================
        // APPLICANT 2
        // ==========================================

        Applicant applicant2 = new Applicant();

        applicant2.setApplicantId(2L);
        applicant2.setApplicationId(1001L);
        applicant2.setCifNo("CIF1000002");

        applicant2.setApplicationRoleId(2L);
        applicant2.setPrimaryApplicantId(1L);
        applicant2.setRelationshipTypeId(3L);
        applicant2.setPartyId(2L);

        applicant2.setShare(50.0);

        applicant2.setVerifiedFlag(true);
        applicant2.setVerificationStatus("VERIFIED");
        applicant2.setVerificationMode("ONLINE");

        applicant2.setValidatedFlag(true);
        applicant2.setValidationStatus("VALIDATED");
        applicant2.setValidationMode("SYSTEM");

        applicant2.setIsActive(true);

        APPLICANTS.put(2L, applicant2);
    }
}