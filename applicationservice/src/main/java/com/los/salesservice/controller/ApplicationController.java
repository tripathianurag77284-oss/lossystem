package com.los.salesservice.controller;

import com.los.salesservice.dto.*;
import com.los.salesservice.service.ApplicationService;
import com.los.salesservice.service.LeadService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {

    private final LeadService leadService;

    private final ApplicationService applicationService;


    @Autowired
    public ApplicationController(
            LeadService leadService, ApplicationService applicationService) {

        this.leadService = leadService;
        this.applicationService = applicationService;
    }


    // =========================================================
    // GET ALL
    // =========================================================

    @GetMapping
    public List<LeadResponse> getAllLeads() {

        return leadService.getAllLeads();
    }


    // =========================================================
    // GET BY ID
    // =========================================================

    @GetMapping("/{leadId}")
    public LeadResponse getLeadById(
            @PathVariable Long leadId) {

        return leadService.getLeadById(
                leadId
        );
    }


    // =========================================================
    // CREATE
    // =========================================================

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LeadResponse createLead(
            @Valid @RequestBody LeadRequest request) {

        return leadService.createLead(
                request
        );
    }


    // =========================================================
    // UPDATE
    // =========================================================

    @PutMapping("/{leadId}")
    public LeadResponse updateLead(
            @PathVariable Long leadId,
            @Valid @RequestBody LeadRequest request) {

        return leadService.updateLead(
                leadId,
                request
        );
    }


    // =========================================================
    // DELETE
    // =========================================================

    @DeleteMapping("/{leadId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLead(
            @PathVariable Long leadId) {

        leadService.deleteLead(
                leadId
        );
    }


    // =========================================================
    // LEAD PROCESSING / STATUS
    // =========================================================

    @PatchMapping("/{leadId}/status")
    public LeadResponse updateLeadStatus(
            @PathVariable Long leadId,
            @Valid @RequestBody
            ApplicationStatusUpdateRequest request) {

        return leadService.updateLeadStatus(
                leadId,
                request
        );
    }


    // =========================================================
    // LEAD TRACKING
    // =========================================================

    @GetMapping("/{leadId}/tracking")
    public List<LeadTrackingResponse> getLeadTracking(
            @PathVariable Long leadId) {

        return leadService.getLeadTracking(
                leadId
        );
    }


// =========================================================
// APPLICATION - GET ALL
// =========================================================

    @GetMapping("/applications")
    public List<ApplicationResponse> getAllApplications() {

        return applicationService.getAllApplications();
    }


// =========================================================
// APPLICATION - GET BY ID
// =========================================================

    @GetMapping("/applications/{applicationId}")
    public ApplicationResponse getApplicationById(
            @PathVariable Long applicationId) {

        return applicationService.getApplicationById(
                applicationId
        );
    }


// =========================================================
// APPLICATION - CREATE
// =========================================================

    @PostMapping("/applications")
    @ResponseStatus(HttpStatus.CREATED)
    public ApplicationResponse createApplication(
            @Valid @RequestBody ApplicationRequest request) {

        return applicationService.createApplication(
                request
        );
    }


// =========================================================
// APPLICATION - UPDATE
// =========================================================

    @PutMapping("/applications/{applicationId}")
    public ApplicationResponse updateApplication(
            @PathVariable Long applicationId,
            @Valid @RequestBody ApplicationRequest request) {

        return applicationService.updateApplication(
                applicationId,
                request
        );
    }


// =========================================================
// APPLICATION - DELETE
// =========================================================

    @DeleteMapping("/applications/{applicationId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteApplication(
            @PathVariable Long applicationId) {

        applicationService.deleteApplication(
                applicationId
        );
    }


// =========================================================
// APPLICATION - UPDATE STATUS
// =========================================================

    @PutMapping("/applications/{applicationId}/status")
    public ApplicationResponse updateApplicationStatus(
            @PathVariable Long applicationId,
            @Valid @RequestBody ApplicationStatusUpdateRequest request) {

        return applicationService.updateApplicationStatus(
                applicationId,
                request
        );
    }
    // =========================================================
// APPLICATION - LOAN PRODUCTS
// =========================================================

    @GetMapping("/applications/{applicationId}/loan-products")
    public List<LoanProductResponse> getLoanProducts(
            @PathVariable Long applicationId) {

        return applicationService.getLoanProducts(applicationId);
    }


// =========================================================
// APPLICATION - LOAN SPECIFICATION
// =========================================================

    @PutMapping("/applications/{applicationId}/loan-specification")
    public ApplicationResponse updateLoanSpecification(
            @PathVariable Long applicationId,
            @Valid @RequestBody LoanSpecificationRequest request) {

        return applicationService.updateLoanSpecification(
                applicationId,
                request
        );
    }


// =========================================================
// APPLICATION - RESUME
// =========================================================

    @PostMapping("/applications/{applicationId}/resume")
    public ApplicationResponse resumeApplication(
            @PathVariable Long applicationId) {

        return applicationService.resumeApplication(applicationId);
    }
}