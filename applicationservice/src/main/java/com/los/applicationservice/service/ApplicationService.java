package com.los.applicationservice.service;

import com.los.applicationservice.dto.*;

import java.util.List;

public interface ApplicationService {

    List<ApplicationResponse> getAllApplications();

    ApplicationResponse getApplicationById(Long applicationId);

    ApplicationResponse createApplication(ApplicationRequest request);

    ApplicationResponse updateApplication(
            Long applicationId,
            ApplicationRequest request
    );

    void deleteApplication(Long applicationId);

    ApplicationResponse updateApplicationStatus(
            Long applicationId,
            ApplicationStatusUpdateRequest request
    );

    List<LoanProductResponse> getAllLoanProducts();

    LoanProductResponse getLoanProductById(Long productId);

    ApplicationResponse updateLoanSpecification(
            Long applicationId,
            LoanSpecificationRequest request
    );

    ApplicationResponse resumeApplication(Long applicationId);

    List<TaskMasterResponse> getAllTasks();

    TaskMasterResponse getTaskById(Long taskId);

    List<TaskStageMasterResponse> getAllTaskStages();

    TaskStageMasterResponse getTaskStageById(Long taskStageId);

    List<ApplicationAssignmentResponse> getAssignmentsByApplicationId(Long applicationId);

    ApplicationAssignmentResponse getAssignmentById(Long assignmentId);

    ApplicationAssignmentResponse createAssignment( Long applicationId, ApplicationAssignmentRequest request );

    ApplicationAssignmentResponse updateAssignmentStatus( Long assignmentId, AssignmentStatusUpdateRequest request );
}

