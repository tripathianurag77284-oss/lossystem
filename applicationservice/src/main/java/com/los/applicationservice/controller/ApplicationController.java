package com.los.applicationservice.controller;

import com.los.applicationservice.dto.*;
import com.los.applicationservice.service.ApplicationService;
import com.los.applicationservice.service.LeadService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApplicationController {

    private final LeadService leadService;
    private final ApplicationService applicationService;

    public ApplicationController(
            LeadService leadService,
            ApplicationService applicationService) {

        this.leadService = leadService;
        this.applicationService = applicationService;
    }


    // =========================================================
    // LEAD
    // =========================================================

    @GetMapping("/leads")
    public List<LeadResponse> getAllLeads() {
        return leadService.getAllLeads();
    }

    @GetMapping("/leads/{leadId}")
    public LeadResponse getLeadById(
            @PathVariable Long leadId) {

        return leadService.getLeadById(leadId);
    }

    @PostMapping("/leads")
    @ResponseStatus(HttpStatus.CREATED)
    public LeadResponse createLead(
            @Valid @RequestBody LeadRequest request) {

        return leadService.createLead(request);
    }

    @PutMapping("/leads/{leadId}")
    public LeadResponse updateLead(
            @PathVariable Long leadId,
            @Valid @RequestBody LeadRequest request) {

        return leadService.updateLead(
                leadId,
                request
        );
    }

    @DeleteMapping("/leads/{leadId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLead(
            @PathVariable Long leadId) {

        leadService.deleteLead(leadId);
    }

    @PatchMapping("/leads/{leadId}/status")
    public LeadResponse updateLeadStatus(
            @PathVariable Long leadId,
            @Valid @RequestBody ApplicationStatusUpdateRequest request) {

        return leadService.updateLeadStatus(
                leadId,
                request
        );
    }

    @GetMapping("/leads/{leadId}/tracking")
    public List<LeadTrackingResponse> getLeadTracking(
            @PathVariable Long leadId) {

        return leadService.getLeadTracking(leadId);
    }


    // =========================================================
    // APPLICATION
    // =========================================================

    @GetMapping("/applications")
    public List<ApplicationResponse> getAllApplications() {
        return applicationService.getAllApplications();
    }

    @GetMapping("/applications/{applicationId}")
    public ApplicationResponse getApplicationById(
            @PathVariable Long applicationId) {

        return applicationService.getApplicationById(
                applicationId
        );
    }

    @PostMapping("/applications")
    @ResponseStatus(HttpStatus.CREATED)
    public ApplicationResponse createApplication(
            @Valid @RequestBody ApplicationRequest request) {

        return applicationService.createApplication(request);
    }

    @PutMapping("/applications/{applicationId}")
    public ApplicationResponse updateApplication(
            @PathVariable Long applicationId,
            @Valid @RequestBody ApplicationRequest request) {

        return applicationService.updateApplication(
                applicationId,
                request
        );
    }

    @DeleteMapping("/applications/{applicationId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteApplication(
            @PathVariable Long applicationId) {

        applicationService.deleteApplication(applicationId);
    }

    @PutMapping("/applications/{applicationId}/status")
    public ApplicationResponse updateApplicationStatus(
            @PathVariable Long applicationId,
            @Valid @RequestBody ApplicationStatusUpdateRequest request) {

        return applicationService.updateApplicationStatus(
                applicationId,
                request
        );
    }

    @PutMapping("/applications/{applicationId}/loan-specification")
    public ApplicationResponse updateLoanSpecification(
            @PathVariable Long applicationId,
            @Valid @RequestBody LoanSpecificationRequest request) {

        return applicationService.updateLoanSpecification(
                applicationId,
                request
        );
    }

    @PostMapping("/applications/{applicationId}/resume")
    public ApplicationResponse resumeApplication(
            @PathVariable Long applicationId) {

        return applicationService.resumeApplication(
                applicationId
        );
    }


    // =========================================================
    // LOAN PRODUCT
    // =========================================================

    @GetMapping("/loan-products")
    public List<LoanProductResponse> getAllLoanProducts() {

        return applicationService.getAllLoanProducts();
    }

    @GetMapping("/loan-products/{productId}")
    public LoanProductResponse getLoanProductById(
            @PathVariable Long productId) {

        return applicationService.getLoanProductById(
                productId
        );
    }


    // =========================================================
    // LEAD ASSIGNMENT
    // =========================================================

    @PostMapping("/leads/{leadId}/assignments")
    @ResponseStatus(HttpStatus.CREATED)
    public LeadAssignmentResponse createLeadAssignment(
            @PathVariable Long leadId,
            @Valid @RequestBody LeadAssignmentRequest request) {

        return leadService.createAssignment(
                leadId,
                request
        );
    }

    @GetMapping("/leads/{leadId}/assignments")
    public List<LeadAssignmentResponse> getLeadAssignments(
            @PathVariable Long leadId) {

        return leadService.getAssignmentsByLeadId(
                leadId
        );
    }

    @GetMapping("/lead-assignments/{assignmentId}")
    public LeadAssignmentResponse getLeadAssignmentById(
            @PathVariable Long assignmentId) {

        return leadService.getAssignmentById(
                assignmentId
        );
    }

    @PutMapping("/lead-assignments/{assignmentId}")
    public LeadAssignmentResponse updateLeadAssignment(
            @PathVariable Long assignmentId,
            @Valid @RequestBody LeadAssignmentRequest request) {

        return leadService.updateAssignment(
                assignmentId,
                request
        );
    }

    @DeleteMapping("/lead-assignments/{assignmentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLeadAssignment(
            @PathVariable Long assignmentId) {

        leadService.deleteAssignment(
                assignmentId
        );
    }


    // =========================================================
    // TASK MASTER
    // =========================================================

    @GetMapping("/tasks")
    public List<TaskMasterResponse> getAllTasks() {

        return applicationService.getAllTasks();
    }

    @GetMapping("/tasks/{taskId}")
    public TaskMasterResponse getTaskById(
            @PathVariable Long taskId) {

        return applicationService.getTaskById(taskId);
    }


    // =========================================================
    // TASK STAGE MASTER
    // =========================================================

    @GetMapping("/task-stages")
    public List<TaskStageMasterResponse> getAllTaskStages() {

        return applicationService.getAllTaskStages();
    }

    @GetMapping("/task-stages/{taskStageId}")
    public TaskStageMasterResponse getTaskStageById(
            @PathVariable Long taskStageId) {

        return applicationService.getTaskStageById(
                taskStageId
        );
    }


    // =========================================================
    // APPLICATION ASSIGNMENT
    // =========================================================

    @GetMapping("/applications/{applicationId}/assignments")
    public List<ApplicationAssignmentResponse>
    getAssignmentsByApplicationId(
            @PathVariable Long applicationId) {

        return applicationService
                .getAssignmentsByApplicationId(
                        applicationId
                );
    }

    @GetMapping("/application-assignments/{assignmentId}")
    public ApplicationAssignmentResponse
    getApplicationAssignmentById(
            @PathVariable Long assignmentId) {

        return applicationService
                .getAssignmentById(
                        assignmentId
                );
    }

    @PostMapping("/applications/{applicationId}/assignments")
    @ResponseStatus(HttpStatus.CREATED)
    public ApplicationAssignmentResponse
    createApplicationAssignment(
            @PathVariable Long applicationId,
            @Valid @RequestBody ApplicationAssignmentRequest request) {

        return applicationService.createAssignment(
                applicationId,
                request
        );
    }

    @PutMapping("/application-assignments/{assignmentId}/status")
    public ApplicationAssignmentResponse
    updateApplicationAssignmentStatus(
            @PathVariable Long assignmentId,
            @Valid @RequestBody AssignmentStatusUpdateRequest request) {

        return applicationService.updateAssignmentStatus(
                assignmentId,
                request
        );
    }
} 