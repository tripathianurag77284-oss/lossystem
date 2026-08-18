package com.los.applicationservice.controller;

import com.los.applicationservice.dto.*;
import com.los.applicationservice.service.ApplicationService;
import com.los.applicationservice.service.LeadService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
// LOAN PRODUCT - GET BY ID
// =========================================================

    @GetMapping("/loan-products/{productId}")
    public LoanProductResponse getLoanProductById(
            @PathVariable Long productId) {

        return applicationService.getLoanProductById(productId);
    }

// =========================================================
// LOAN PRODUCT - GET ALL
// =========================================================

    @GetMapping("/loan-products")
    public List<LoanProductResponse> getAllLoanProducts() {

        return applicationService.getAllLoanProducts();
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


// =========================================================
// LEAD ASSIGNMENT - CREATE
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


// =========================================================
// LEAD ASSIGNMENT - GET ALL FOR LEAD
// =========================================================

    @GetMapping("/leads/{leadId}/assignments")
    public List<LeadAssignmentResponse> getLeadAssignments(
            @PathVariable Long leadId) {

        return leadService.getAssignmentsByLeadId(
                leadId
        );
    }


// =========================================================
// LEAD ASSIGNMENT - GET BY ID
// =========================================================

    @GetMapping("/assignments/{assignmentId}")
    public LeadAssignmentResponse getLeadAssignmentById(
            @PathVariable Long assignmentId) {

        return leadService.getAssignmentById(
                assignmentId
        );
    }


// =========================================================
// LEAD ASSIGNMENT - UPDATE
// =========================================================

    @PutMapping("/assignments/{assignmentId}")
    public LeadAssignmentResponse updateLeadAssignment(
            @PathVariable Long assignmentId,
            @Valid @RequestBody LeadAssignmentRequest request) {

        return leadService.updateAssignment(
                assignmentId,
                request
        );
    }


// =========================================================
// LEAD ASSIGNMENT - DELETE
// =========================================================

    @DeleteMapping("/assignments/{assignmentId}")
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

        return applicationService.getTaskStageById(taskStageId);
    }


// =========================================================
// APPLICATION ASSIGNMENT
// =========================================================

    @GetMapping("/applications/{applicationId}/assignments")
    public List<ApplicationAssignmentResponse>
    getAssignmentsByApplicationId(
            @PathVariable Long applicationId) {

        return applicationService
                .getAssignmentsByApplicationId(applicationId);
    }


    @GetMapping("/application-assignments/{assignmentId}")
    public ApplicationAssignmentResponse
    getApplicationAssignmentById(
            @PathVariable Long assignmentId) {

        return applicationService
                .getAssignmentById(assignmentId);
    }


    @PostMapping("/applications/{applicationId}/assignments")
    @ResponseStatus(HttpStatus.CREATED)
    public ApplicationAssignmentResponse
    createApplicationAssignment(
            @PathVariable Long applicationId,
            @Valid @RequestBody
            ApplicationAssignmentRequest request) {

        return applicationService
                .createAssignment(
                        applicationId,
                        request
                );
    }


    @PutMapping("/application-assignments/{assignmentId}/status")
    public ApplicationAssignmentResponse
    updateApplicationAssignmentStatus(
            @PathVariable Long assignmentId,
            @Valid @RequestBody
            AssignmentStatusUpdateRequest request) {

        return applicationService
                .updateAssignmentStatus(
                        assignmentId,
                        request
                );
    }


}