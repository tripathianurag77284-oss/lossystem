package com.los.salesservice.service;

import com.los.salesservice.dto.*;

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

    List<LoanProductResponse> getLoanProducts(Long applicationId);

    ApplicationResponse updateLoanSpecification(
            Long applicationId,
            LoanSpecificationRequest request
    );

    ApplicationResponse resumeApplication(Long applicationId);
}