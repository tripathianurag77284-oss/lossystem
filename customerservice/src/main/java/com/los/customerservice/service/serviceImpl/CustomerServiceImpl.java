package com.los.customerservice.service.serviceImpl;

import com.los.customerservice.dto.*;
import com.los.customerservice.model.*;
import com.los.customerservice.repository.*;
import com.los.customerservice.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final OccupationRepository occupationRepository;
    private final DocumentTypeRepository documentTypeRepository;
    private final ApplicationRoleRepository applicationRoleRepository;
    private final RelationshipTypeRepository relationshipTypeRepository;
    private final PartyRepository partyRepository;
    private final ApplicantRepository applicantRepository;


    // =========================================================
    // OCCUPATION
    // =========================================================

    @Override
    public List<OccupationResponse> getAllOccupations() {

        return occupationRepository.findAll()
                .stream()
                .map(this::toOccupationResponse)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<OccupationResponse> getOccupationById(Long id) {

        return occupationRepository.findById(id)
                .map(this::toOccupationResponse);
    }

    @Override
    public OccupationResponse createOccupation(
            OccupationRequest request) {

        Occupation occupation = new Occupation();

        occupation.setOccupationName(
                request.getOccupationName());

        occupation.setOccupationCategory(
                request.getOccupationCategory());

        occupation.setIsActive(
                request.getIsActive());

        Occupation saved =
                occupationRepository.save(occupation);

        return toOccupationResponse(saved);
    }

    @Override
    public Optional<OccupationResponse> updateOccupation(
            Long id,
            OccupationRequest request) {

        return occupationRepository.findById(id)
                .map(occupation -> {

                    occupation.setOccupationName(
                            request.getOccupationName());

                    occupation.setOccupationCategory(
                            request.getOccupationCategory());

                    occupation.setIsActive(
                            request.getIsActive());

                    Occupation updated =
                            occupationRepository.save(occupation);

                    return toOccupationResponse(updated);
                });
    }

    @Override
    public boolean deleteOccupation(Long id) {

        if (!occupationRepository.existsById(id)) {
            return false;
        }

        occupationRepository.deleteById(id);
        return true;
    }


    // =========================================================
    // DOCUMENT TYPE
    // =========================================================

    @Override
    public List<DocumentTypeResponse> getAllDocumentTypes() {

        return documentTypeRepository.findAll()
                .stream()
                .map(this::toDocumentTypeResponse)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<DocumentTypeResponse> getDocumentTypeById(
            Long id) {

        return documentTypeRepository.findById(id)
                .map(this::toDocumentTypeResponse);
    }

    @Override
    public DocumentTypeResponse createDocumentType(
            DocumentTypeRequest request) {

        DocumentType documentType = new DocumentType();

        documentType.setDocumentName(
                request.getDocumentName());

        documentType.setCategory(
                request.getCategory());

        documentType.setIsPoi(
                request.getIsPoi());

        documentType.setIsPoa(
                request.getIsPoa());

        documentType.setIsFacialDocument(
                request.getIsFacialDocument());

        documentType.setIsActive(
                request.getIsActive());

        DocumentType saved =
                documentTypeRepository.save(documentType);

        return toDocumentTypeResponse(saved);
    }

    @Override
    public Optional<DocumentTypeResponse> updateDocumentType(
            Long id,
            DocumentTypeRequest request) {

        return documentTypeRepository.findById(id)
                .map(documentType -> {

                    documentType.setDocumentName(
                            request.getDocumentName());

                    documentType.setCategory(
                            request.getCategory());

                    documentType.setIsPoi(
                            request.getIsPoi());

                    documentType.setIsPoa(
                            request.getIsPoa());

                    documentType.setIsFacialDocument(
                            request.getIsFacialDocument());

                    documentType.setIsActive(
                            request.getIsActive());

                    DocumentType updated =
                            documentTypeRepository.save(documentType);

                    return toDocumentTypeResponse(updated);
                });
    }

    @Override
    public boolean deleteDocumentType(Long id) {

        if (!documentTypeRepository.existsById(id)) {
            return false;
        }

        documentTypeRepository.deleteById(id);
        return true;
    }


    // =========================================================
    // APPLICATION ROLE
    // =========================================================

    @Override
    public List<ApplicationRoleResponse> getAllApplicationRoles() {

        return applicationRoleRepository.findAll()
                .stream()
                .map(this::toApplicationRoleResponse)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ApplicationRoleResponse>
    getApplicationRoleById(Long id) {

        return applicationRoleRepository.findById(id)
                .map(this::toApplicationRoleResponse);
    }

    @Override
    public ApplicationRoleResponse createApplicationRole(
            ApplicationRoleRequest request) {

        ApplicationRole role = new ApplicationRole();

        role.setRoleName(
                request.getRoleName());

        role.setRoleDescription(
                request.getRoleDescription());

        ApplicationRole saved =
                applicationRoleRepository.save(role);

        return toApplicationRoleResponse(saved);
    }

    @Override
    public Optional<ApplicationRoleResponse>
    updateApplicationRole(
            Long id,
            ApplicationRoleRequest request) {

        return applicationRoleRepository.findById(id)
                .map(role -> {

                    role.setRoleName(
                            request.getRoleName());

                    role.setRoleDescription(
                            request.getRoleDescription());

                    ApplicationRole updated =
                            applicationRoleRepository.save(role);

                    return toApplicationRoleResponse(updated);
                });
    }

    @Override
    public boolean deleteApplicationRole(Long id) {

        if (!applicationRoleRepository.existsById(id)) {
            return false;
        }

        applicationRoleRepository.deleteById(id);
        return true;
    }


    // =========================================================
    // RELATIONSHIP TYPE
    // =========================================================

    @Override
    public List<RelationshipTypeResponse>
    getAllRelationshipTypes() {

        return relationshipTypeRepository.findAll()
                .stream()
                .map(this::toRelationshipTypeResponse)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<RelationshipTypeResponse>
    getRelationshipTypeById(Long id) {

        return relationshipTypeRepository.findById(id)
                .map(this::toRelationshipTypeResponse);
    }

    @Override
    public RelationshipTypeResponse createRelationshipType(
            RelationshipTypeRequest request) {

        RelationshipType relationshipType =
                new RelationshipType();

        relationshipType.setRelationshipType(
                request.getRelationshipType());

        relationshipType.setRelationshipDescription(
                request.getRelationshipDescription());

        RelationshipType saved =
                relationshipTypeRepository.save(
                        relationshipType);

        return toRelationshipTypeResponse(saved);
    }

    @Override
    public Optional<RelationshipTypeResponse>
    updateRelationshipType(
            Long id,
            RelationshipTypeRequest request) {

        return relationshipTypeRepository.findById(id)
                .map(relationshipType -> {

                    relationshipType.setRelationshipType(
                            request.getRelationshipType());

                    relationshipType.setRelationshipDescription(
                            request.getRelationshipDescription());

                    RelationshipType updated =
                            relationshipTypeRepository.save(
                                    relationshipType);

                    return toRelationshipTypeResponse(updated);
                });
    }

    @Override
    public boolean deleteRelationshipType(Long id) {

        if (!relationshipTypeRepository.existsById(id)) {
            return false;
        }

        relationshipTypeRepository.deleteById(id);
        return true;
    }


    // =========================================================
    // PARTY
    // =========================================================

    @Override
    public List<PartyResponse> getAllParties() {

        return partyRepository.findAll()
                .stream()
                .map(this::toPartyResponse)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<PartyResponse> getPartyById(Long id) {

        return partyRepository.findById(id)
                .map(this::toPartyResponse);
    }

    @Override
    public PartyResponse createParty(
            PartyRequest request) {

        Party party = new Party();

        party.setFirstName(request.getFirstName());
        party.setMiddleName(request.getMiddleName());
        party.setLastName(request.getLastName());

        party.setFatherName(request.getFatherName());
        party.setSpouseName(request.getSpouseName());

        party.setMobile(request.getMobile());
        party.setAlternateMobile(
                request.getAlternateMobile());

        party.setEmail(request.getEmail());
        party.setDob(request.getDob());

        party.setPan(request.getPan());
        party.setAadhar(request.getAadhar());

        party.setOccupationId(
                request.getOccupationId());

        party.setOtherOccupation(
                request.getOtherOccupation());

        party.setMaritalStatus(
                request.getMaritalStatus());

        party.setNumberOfDependent(
                request.getNumberOfDependent());

        party.setEducationType(
                request.getEducationType());

        party.setEducation(
                request.getEducation());

        party.setEduDocId(
                request.getEduDocId());

        party.setPoiDocId(
                request.getPoiDocId());

        party.setPoaDocId(
                request.getPoaDocId());

        party.setPhotoDocId(
                request.getPhotoDocId());

        party.setIsMobileVerified(false);
        party.setIsAlternateMobileVerified(false);
        party.setIsEmailVerified(false);
        party.setIsDobVerified(false);
        party.setIsPanVerified(false);
        party.setIsAadharVerified(false);

        party.setVerifiedFlag(false);
        party.setValidatedFlag(false);
        party.setIsActive(true);

        Party saved = partyRepository.save(party);

        return toPartyResponse(saved);
    }

    @Override
    public Optional<PartyResponse> updateParty(
            Long id,
            PartyRequest request) {

        return partyRepository.findById(id)
                .map(party -> {

                    party.setFirstName(
                            request.getFirstName());

                    party.setMiddleName(
                            request.getMiddleName());

                    party.setLastName(
                            request.getLastName());

                    party.setFatherName(
                            request.getFatherName());

                    party.setSpouseName(
                            request.getSpouseName());

                    party.setMobile(
                            request.getMobile());

                    party.setAlternateMobile(
                            request.getAlternateMobile());

                    party.setEmail(
                            request.getEmail());

                    party.setDob(
                            request.getDob());

                    party.setPan(
                            request.getPan());

                    party.setAadhar(
                            request.getAadhar());

                    party.setOccupationId(
                            request.getOccupationId());

                    party.setOtherOccupation(
                            request.getOtherOccupation());

                    party.setMaritalStatus(
                            request.getMaritalStatus());

                    party.setNumberOfDependent(
                            request.getNumberOfDependent());

                    party.setEducationType(
                            request.getEducationType());

                    party.setEducation(
                            request.getEducation());

                    party.setEduDocId(
                            request.getEduDocId());

                    party.setPoiDocId(
                            request.getPoiDocId());

                    party.setPoaDocId(
                            request.getPoaDocId());

                    party.setPhotoDocId(
                            request.getPhotoDocId());

                    Party updated =
                            partyRepository.save(party);

                    return toPartyResponse(updated);
                });
    }

    @Override
    public boolean deleteParty(Long id) {

        if (!partyRepository.existsById(id)) {
            return false;
        }

        partyRepository.deleteById(id);
        return true;
    }


    // =========================================================
    // APPLICANT
    // =========================================================

    @Override
    public List<ApplicantResponse> getAllApplicants() {

        return applicantRepository.findAll()
                .stream()
                .map(this::toApplicantResponse)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ApplicantResponse> getApplicantById(
            Long id) {

        return applicantRepository.findById(id)
                .map(this::toApplicantResponse);
    }

    @Override
    public List<ApplicantResponse>
    getApplicantsByApplicationId(
            Long applicationId) {

        return applicantRepository
                .findByApplicationId(applicationId)
                .stream()
                .map(this::toApplicantResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ApplicantResponse createApplicant(
            ApplicantRequest request) {

        Applicant applicant = new Applicant();

        applicant.setApplicationId(
                request.getApplicationId());

        applicant.setCifNo(
                request.getCifNo());

        applicant.setApplicationRoleId(
                request.getApplicationRoleId());

        applicant.setPrimaryApplicantId(
                request.getPrimaryApplicantId());

        applicant.setRelationshipTypeId(
                request.getRelationshipTypeId());

        applicant.setPartyId(
                request.getPartyId());

        applicant.setShare(
                request.getShare());

        applicant.setVerifiedFlag(false);
        applicant.setValidatedFlag(false);
        applicant.setIsActive(true);

        Applicant saved =
                applicantRepository.save(applicant);

        return toApplicantResponse(saved);
    }

    @Override
    public Optional<ApplicantResponse> updateApplicant(
            Long id,
            ApplicantRequest request) {

        return applicantRepository.findById(id)
                .map(applicant -> {

                    applicant.setApplicationId(
                            request.getApplicationId());

                    applicant.setCifNo(
                            request.getCifNo());

                    applicant.setApplicationRoleId(
                            request.getApplicationRoleId());

                    applicant.setPrimaryApplicantId(
                            request.getPrimaryApplicantId());

                    applicant.setRelationshipTypeId(
                            request.getRelationshipTypeId());

                    applicant.setPartyId(
                            request.getPartyId());

                    applicant.setShare(
                            request.getShare());

                    Applicant updated =
                            applicantRepository.save(applicant);

                    return toApplicantResponse(updated);
                });
    }

    @Override
    public boolean deleteApplicant(Long id) {

        if (!applicantRepository.existsById(id)) {
            return false;
        }

        applicantRepository.deleteById(id);
        return true;
    }


    // =========================================================
    // MAPPERS
    // =========================================================

    private OccupationResponse toOccupationResponse(
            Occupation occupation) {

        return OccupationResponse.builder()
                .occupationId(
                        occupation.getOccupationId())
                .occupationName(
                        occupation.getOccupationName())
                .occupationCategory(
                        occupation.getOccupationCategory())
                .isActive(
                        occupation.getIsActive())
                .build();
    }


    private DocumentTypeResponse toDocumentTypeResponse(
            DocumentType documentType) {

        return DocumentTypeResponse.builder()
                .documentTypeId(
                        documentType.getDocumentTypeId())
                .documentName(
                        documentType.getDocumentName())
                .category(
                        documentType.getCategory())
                .isPoi(
                        documentType.getIsPoi())
                .isPoa(
                        documentType.getIsPoa())
                .isFacialDocument(
                        documentType.getIsFacialDocument())
                .isActive(
                        documentType.getIsActive())
                .build();
    }


    private ApplicationRoleResponse
    toApplicationRoleResponse(
            ApplicationRole role) {

        return ApplicationRoleResponse.builder()
                .roleId(
                        role.getRoleId())
                .roleName(
                        role.getRoleName())
                .roleDescription(
                        role.getRoleDescription())
                .build();
    }


    private RelationshipTypeResponse
    toRelationshipTypeResponse(
            RelationshipType relationshipType) {

        return RelationshipTypeResponse.builder()
                .relationshipTypeId(
                        relationshipType
                                .getRelationshipTypeId())
                .relationshipType(
                        relationshipType
                                .getRelationshipType())
                .relationshipDescription(
                        relationshipType
                                .getRelationshipDescription())
                .build();
    }


    private PartyResponse toPartyResponse(
            Party party) {

        return PartyResponse.builder()

                .partyId(
                        party.getPartyId())

                .firstName(
                        party.getFirstName())

                .middleName(
                        party.getMiddleName())

                .lastName(
                        party.getLastName())

                .fatherName(
                        party.getFatherName())

                .spouseName(
                        party.getSpouseName())

                .mobile(
                        party.getMobile())

                .isMobileVerified(
                        party.getIsMobileVerified())

                .alternateMobile(
                        party.getAlternateMobile())

                .isAlternateMobileVerified(
                        party.getIsAlternateMobileVerified())

                .email(
                        party.getEmail())

                .isEmailVerified(
                        party.getIsEmailVerified())

                .dob(
                        party.getDob())

                .isDobVerified(
                        party.getIsDobVerified())

                .pan(
                        party.getPan())

                .isPanVerified(
                        party.getIsPanVerified())

                .aadhar(
                        party.getAadhar())

                .isAadharVerified(
                        party.getIsAadharVerified())

                .occupationId(
                        party.getOccupationId())

                .otherOccupation(
                        party.getOtherOccupation())

                .maritalStatus(
                        party.getMaritalStatus())

                .numberOfDependent(
                        party.getNumberOfDependent())

                .educationType(
                        party.getEducationType())

                .education(
                        party.getEducation())

                .eduDocId(
                        party.getEduDocId())

                .poiDocId(
                        party.getPoiDocId())

                .poaDocId(
                        party.getPoaDocId())

                .photoDocId(
                        party.getPhotoDocId())

                .verifiedFlag(
                        party.getVerifiedFlag())

                .verificationRemark(
                        party.getVerificationRemark())

                .verificationStatus(
                        party.getVerificationStatus())

                .verificationMode(
                        party.getVerificationMode())

                .validatedFlag(
                        party.getValidatedFlag())

                .validationRemark(
                        party.getValidationRemark())

                .validationStatus(
                        party.getValidationStatus())

                .validationMode(
                        party.getValidationMode())

                .isActive(
                        party.getIsActive())

                .build();
    }


    private ApplicantResponse toApplicantResponse(
            Applicant applicant) {

        return ApplicantResponse.builder()

                .applicantId(
                        applicant.getApplicantId())

                .applicationId(
                        applicant.getApplicationId())

                .cifNo(
                        applicant.getCifNo())

                .applicationRoleId(
                        applicant.getApplicationRoleId())

                .primaryApplicantId(
                        applicant.getPrimaryApplicantId())

                .relationshipTypeId(
                        applicant.getRelationshipTypeId())

                .partyId(
                        applicant.getPartyId())

                .share(
                        applicant.getShare())

                .verifiedFlag(
                        applicant.getVerifiedFlag())

                .verificationRemark(
                        applicant.getVerificationRemark())

                .verificationStatus(
                        applicant.getVerificationStatus())

                .verificationMode(
                        applicant.getVerificationMode())

                .validatedFlag(
                        applicant.getValidatedFlag())

                .validationRemark(
                        applicant.getValidationRemark())

                .validationStatus(
                        applicant.getValidationStatus())

                .validationMode(
                        applicant.getValidationMode())

                .isActive(
                        applicant.getIsActive())

                .build();
    }
}