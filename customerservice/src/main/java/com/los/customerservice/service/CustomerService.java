package com.los.customerservice.service;

import com.los.customerservice.dto.*;
import com.los.customerservice.dto.*;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

    // =========================================================
    // OCCUPATION
    // =========================================================

    List<OccupationResponse> getAllOccupations();

    Optional<OccupationResponse> getOccupationById(Long id);

    OccupationResponse createOccupation(
            OccupationRequest request);

    Optional<OccupationResponse> updateOccupation(
            Long id,
            OccupationRequest request);

    boolean deleteOccupation(Long id);


    // =========================================================
    // DOCUMENT TYPE
    // =========================================================

    List<DocumentTypeResponse> getAllDocumentTypes();

    Optional<DocumentTypeResponse> getDocumentTypeById(Long id);

    DocumentTypeResponse createDocumentType(
            DocumentTypeRequest request);

    Optional<DocumentTypeResponse> updateDocumentType(
            Long id,
            DocumentTypeRequest request);

    boolean deleteDocumentType(Long id);


    // =========================================================
    // APPLICATION ROLE
    // =========================================================

    List<ApplicationRoleResponse> getAllApplicationRoles();

    Optional<ApplicationRoleResponse> getApplicationRoleById(
            Long id);

    ApplicationRoleResponse createApplicationRole(
            ApplicationRoleRequest request);

    Optional<ApplicationRoleResponse> updateApplicationRole(
            Long id,
            ApplicationRoleRequest request);

    boolean deleteApplicationRole(Long id);


    // =========================================================
    // RELATIONSHIP TYPE
    // =========================================================

    List<RelationshipTypeResponse> getAllRelationshipTypes();

    Optional<RelationshipTypeResponse> getRelationshipTypeById(
            Long id);

    RelationshipTypeResponse createRelationshipType(
            RelationshipTypeRequest request);

    Optional<RelationshipTypeResponse> updateRelationshipType(
            Long id,
            RelationshipTypeRequest request);

    boolean deleteRelationshipType(Long id);


    // =========================================================
    // PARTY
    // =========================================================

    List<PartyResponse> getAllParties();

    Optional<PartyResponse> getPartyById(Long id);

    PartyResponse createParty(
            PartyRequest request);

    Optional<PartyResponse> updateParty(
            Long id,
            PartyRequest request);

    boolean deleteParty(Long id);


    // =========================================================
    // APPLICANT
    // =========================================================

    List<ApplicantResponse> getAllApplicants();

    Optional<ApplicantResponse> getApplicantById(Long id);

    List<ApplicantResponse> getApplicantsByApplicationId(
            Long applicationId);

    ApplicantResponse createApplicant(
            ApplicantRequest request);

    Optional<ApplicantResponse> updateApplicant(
            Long id,
            ApplicantRequest request);

    boolean deleteApplicant(Long id);
}