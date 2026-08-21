package com.los.customerservice.controller;

import com.los.customerservice.dto.*;
import com.los.customerservice.dto.*;
import com.los.customerservice.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    // =========================================================
    // OCCUPATION APIs
    // =========================================================

    @GetMapping("/occupations")
    public ResponseEntity<List<OccupationResponse>> getOccupations() {

        return ResponseEntity.ok(
                customerService.getAllOccupations()
        );
    }

    @GetMapping("/occupations/{id}")
    public ResponseEntity<OccupationResponse> getOccupation(
            @PathVariable Long id) {

        return customerService
                .getOccupationById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/occupations")
    public ResponseEntity<OccupationResponse> createOccupation(
            @RequestBody OccupationRequest request) {

        return ResponseEntity.ok(
                customerService.createOccupation(request)
        );
    }

    @PutMapping("/occupations/{id}")
    public ResponseEntity<OccupationResponse> updateOccupation(
            @PathVariable Long id,
            @RequestBody OccupationRequest request) {

        return customerService
                .updateOccupation(id, request)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/occupations/{id}")
    public ResponseEntity<Void> deleteOccupation(
            @PathVariable Long id) {

        return customerService.deleteOccupation(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }


    // =========================================================
    // DOCUMENT TYPE APIs
    // =========================================================

    @GetMapping("/document-types")
    public ResponseEntity<List<DocumentTypeResponse>> getDocumentTypes() {

        return ResponseEntity.ok(
                customerService.getAllDocumentTypes()
        );
    }

    @GetMapping("/document-types/{id}")
    public ResponseEntity<DocumentTypeResponse> getDocumentType(
            @PathVariable Long id) {

        return customerService
                .getDocumentTypeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/document-types")
    public ResponseEntity<DocumentTypeResponse> createDocumentType(
            @RequestBody DocumentTypeRequest request) {

        return ResponseEntity.ok(
                customerService.createDocumentType(request)
        );
    }

    @PutMapping("/document-types/{id}")
    public ResponseEntity<DocumentTypeResponse> updateDocumentType(
            @PathVariable Long id,
            @RequestBody DocumentTypeRequest request) {

        return customerService
                .updateDocumentType(id, request)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/document-types/{id}")
    public ResponseEntity<Void> deleteDocumentType(
            @PathVariable Long id) {

        return customerService.deleteDocumentType(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }


    // =========================================================
    // APPLICATION ROLE APIs
    // =========================================================

    @GetMapping("/application-roles")
    public ResponseEntity<List<ApplicationRoleResponse>>
    getApplicationRoles() {

        return ResponseEntity.ok(
                customerService.getAllApplicationRoles()
        );
    }

    @GetMapping("/application-roles/{id}")
    public ResponseEntity<ApplicationRoleResponse>
    getApplicationRole(@PathVariable Long id) {

        return customerService
                .getApplicationRoleById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/application-roles")
    public ResponseEntity<ApplicationRoleResponse>
    createApplicationRole(
            @RequestBody ApplicationRoleRequest request) {

        return ResponseEntity.ok(
                customerService.createApplicationRole(request)
        );
    }

    @PutMapping("/application-roles/{id}")
    public ResponseEntity<ApplicationRoleResponse>
    updateApplicationRole(
            @PathVariable Long id,
            @RequestBody ApplicationRoleRequest request) {

        return customerService
                .updateApplicationRole(id, request)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/application-roles/{id}")
    public ResponseEntity<Void> deleteApplicationRole(
            @PathVariable Long id) {

        return customerService.deleteApplicationRole(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }


    // =========================================================
    // RELATIONSHIP TYPE APIs
    // =========================================================

    @GetMapping("/relationship-types")
    public ResponseEntity<List<RelationshipTypeResponse>>
    getRelationshipTypes() {

        return ResponseEntity.ok(
                customerService.getAllRelationshipTypes()
        );
    }

    @GetMapping("/relationship-types/{id}")
    public ResponseEntity<RelationshipTypeResponse>
    getRelationshipType(@PathVariable Long id) {

        return customerService
                .getRelationshipTypeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/relationship-types")
    public ResponseEntity<RelationshipTypeResponse>
    createRelationshipType(
            @RequestBody RelationshipTypeRequest request) {

        return ResponseEntity.ok(
                customerService.createRelationshipType(request)
        );
    }

    @PutMapping("/relationship-types/{id}")
    public ResponseEntity<RelationshipTypeResponse>
    updateRelationshipType(
            @PathVariable Long id,
            @RequestBody RelationshipTypeRequest request) {

        return customerService
                .updateRelationshipType(id, request)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/relationship-types/{id}")
    public ResponseEntity<Void> deleteRelationshipType(
            @PathVariable Long id) {

        return customerService.deleteRelationshipType(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }


    // =========================================================
    // PARTY APIs
    // =========================================================

    @GetMapping("/parties")
    public ResponseEntity<List<PartyResponse>> getParties() {

        return ResponseEntity.ok(
                customerService.getAllParties()
        );
    }

    @GetMapping("/parties/{id}")
    public ResponseEntity<PartyResponse> getParty(
            @PathVariable Long id) {

        return customerService
                .getPartyById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/parties")
    public ResponseEntity<PartyResponse> createParty(
            @RequestBody PartyRequest request) {

        return ResponseEntity.ok(
                customerService.createParty(request)
        );
    }

    @PutMapping("/parties/{id}")
    public ResponseEntity<PartyResponse> updateParty(
            @PathVariable Long id,
            @RequestBody PartyRequest request) {

        return customerService
                .updateParty(id, request)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/parties/{id}")
    public ResponseEntity<Void> deleteParty(
            @PathVariable Long id) {

        return customerService.deleteParty(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }


    // =========================================================
    // APPLICANT APIs
    // =========================================================

    @GetMapping("/applicants")
    public ResponseEntity<List<ApplicantResponse>> getApplicants() {

        return ResponseEntity.ok(
                customerService.getAllApplicants()
        );
    }

    @GetMapping("/applicants/{id}")
    public ResponseEntity<ApplicantResponse> getApplicant(
            @PathVariable Long id) {

        return customerService
                .getApplicantById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/applicants/application/{applicationId}")
    public ResponseEntity<List<ApplicantResponse>>
    getApplicantsByApplication(
            @PathVariable Long applicationId) {

        return ResponseEntity.ok(
                customerService.getApplicantsByApplicationId(
                        applicationId
                )
        );
    }

    @PostMapping("/applicants")
    public ResponseEntity<ApplicantResponse> createApplicant(
            @RequestBody ApplicantRequest request) {

        return ResponseEntity.ok(
                customerService.createApplicant(request)
        );
    }

    @PutMapping("/applicants/{id}")
    public ResponseEntity<ApplicantResponse> updateApplicant(
            @PathVariable Long id,
            @RequestBody ApplicantRequest request) {

        return customerService
                .updateApplicant(id, request)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/applicants/{id}")
    public ResponseEntity<Void> deleteApplicant(
            @PathVariable Long id) {

        return customerService.deleteApplicant(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}