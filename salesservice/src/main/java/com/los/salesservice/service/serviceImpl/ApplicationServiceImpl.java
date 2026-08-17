package com.los.salesservice.service.serviceImpl;

import com.los.salesservice.dto.ApplicationRequest;
import com.los.salesservice.dto.ApplicationResponse;
import com.los.salesservice.dto.ApplicationStatusUpdateRequest;
import com.los.salesservice.dto.LoanProductResponse;
import com.los.salesservice.dto.LoanSpecificationRequest;
import com.los.salesservice.exception.ApplicationNotFoundException;
import com.los.salesservice.kafka.KafkaProducerService;
import com.los.salesservice.kafka.event.ApplicationCreatedEvent;
import com.los.salesservice.kafka.event.ApplicationStatusChangedEvent;
import com.los.salesservice.mock.ApplicationMockData;
import com.los.salesservice.mock.LoanProductMockData;
import com.los.salesservice.model.Application;
import com.los.salesservice.model.ApplicationStatus;
import com.los.salesservice.service.ApplicationService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    private final List<Application> applications =
            ApplicationMockData.getApplications();

    private final KafkaProducerService kafkaProducerService;

    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public ApplicationServiceImpl(
            KafkaProducerService kafkaProducerService) {

        this.kafkaProducerService = kafkaProducerService;
    }

    // =========================================================
    // GET ALL APPLICATIONS
    // =========================================================

    @Override
    public List<ApplicationResponse> getAllApplications() {

        return applications.stream()
                .map(this::mapToResponse)
                .toList();
    }

    // =========================================================
    // GET APPLICATION BY ID
    // =========================================================

    @Override
    public ApplicationResponse getApplicationById(
            Long applicationId) {

        Application application =
                findApplication(applicationId);

        return mapToResponse(application);
    }

    // =========================================================
    // CREATE APPLICATION
    // =========================================================

    @Override
    public ApplicationResponse createApplication(
            ApplicationRequest request) {

        long newId = applications.stream()
                .mapToLong(Application::getApplicationId)
                .max()
                .orElse(5000L) + 1;

        Application application = new Application();

        application.setApplicationId(newId);

        application.setLeadId(
                request.getLeadId()
        );

        application.setCustomerId(
                request.getCustomerId()
        );

        application.setApplicationNumber(
                "APP-" + newId
        );

        application.setLoanProduct(
                request.getLoanProduct()
        );

        application.setLoanAmount(
                request.getLoanAmount()
        );

        application.setLoanTenure(
                request.getLoanTenure()
        );

        application.setInterestRate(
                request.getInterestRate()
        );

        application.setStatus(
                ApplicationStatus.DRAFT
        );

        application.setDescription(
                request.getDescription()
        );

        application.setCreatedAt(
                LocalDateTime.now()
        );

        application.setModifiedAt(
                LocalDateTime.now()
        );

        // Add to mock list
        applications.add(application);

        // =====================================================
        // SEND KAFKA EVENT
        // =====================================================

        ApplicationCreatedEvent event =
                new ApplicationCreatedEvent(
                        application.getApplicationId(),
                        application.getLeadId(),
                        application.getCustomerId(),
                        application.getApplicationNumber(),
                        application.getLoanProduct(),
                        application.getLoanAmount(),
                        application.getLoanTenure(),
                        application.getInterestRate(),
                        application.getStatus(),
                        application.getCreatedAt()
                );

        kafkaProducerService.sendApplicationCreatedEvent(
                event
        );

        return mapToResponse(application);
    }

    // =========================================================
    // UPDATE APPLICATION
    // =========================================================

    @Override
    public ApplicationResponse updateApplication(
            Long applicationId,
            ApplicationRequest request) {

        Application application =
                findApplication(applicationId);

        application.setLeadId(
                request.getLeadId()
        );

        application.setCustomerId(
                request.getCustomerId()
        );

        application.setLoanProduct(
                request.getLoanProduct()
        );

        application.setLoanAmount(
                request.getLoanAmount()
        );

        application.setLoanTenure(
                request.getLoanTenure()
        );

        application.setInterestRate(
                request.getInterestRate()
        );

        application.setDescription(
                request.getDescription()
        );

        application.setModifiedAt(
                LocalDateTime.now()
        );

        return mapToResponse(application);
    }

    // =========================================================
    // DELETE APPLICATION
    // =========================================================

    @Override
    public void deleteApplication(
            Long applicationId) {

        Application application =
                findApplication(applicationId);

        applications.remove(application);
    }

    // =========================================================
    // UPDATE APPLICATION STATUS
    // =========================================================

    @Override
    public ApplicationResponse updateApplicationStatus(
            Long applicationId,
            ApplicationStatusUpdateRequest request) {

        Application application =
                findApplication(applicationId);

        // Store old status
        ApplicationStatus oldStatus =
                application.getStatus();

        // New status
        ApplicationStatus newStatus =
                request.getStatus();

        // Update status
        application.setStatus(
                newStatus
        );

        application.setModifiedAt(
                LocalDateTime.now()
        );

        // =====================================================
        // SEND STATUS CHANGE EVENT
        // =====================================================

        ApplicationStatusChangedEvent event =
                new ApplicationStatusChangedEvent(
                        application.getApplicationId(),
                        application.getLeadId(),
                        oldStatus,
                        newStatus,
                        application.getModifiedAt()
                );

        kafkaProducerService
                .sendApplicationStatusChangedEvent(event);

        return mapToResponse(application);
    }

    // =========================================================
    // GET LOAN PRODUCTS
    // =========================================================

    @Override
    public List<LoanProductResponse> getLoanProducts(
            Long applicationId) {

        // Verify application exists
        findApplication(applicationId);

        return LoanProductMockData.getLoanProducts();
    }

    // =========================================================
    // UPDATE LOAN SPECIFICATION
    // =========================================================

    @Override
    public ApplicationResponse updateLoanSpecification(
            Long applicationId,
            LoanSpecificationRequest request) {

        Application application =
                findApplication(applicationId);

        application.setLoanProduct(
                request.getLoanProduct()
        );

        application.setLoanAmount(
                request.getLoanAmount()
        );

        application.setLoanTenure(
                request.getLoanTenure()
        );

        application.setInterestRate(
                request.getInterestRate()
        );

        application.setModifiedAt(
                LocalDateTime.now()
        );

        return mapToResponse(application);
    }

    // =========================================================
    // RESUME APPLICATION
    // =========================================================

    @Override
    public ApplicationResponse resumeApplication(Long applicationId) {

        Application application = findApplication(applicationId);

        // Only DRAFT applications can be resumed
        if (application.getStatus() != ApplicationStatus.DRAFT) {
            throw new RuntimeException(
                    "Only DRAFT applications can be resumed"
            );
        }

        // Change status
        application.setStatus(ApplicationStatus.IN_PROGRESS);

        // Update modification time
        application.setModifiedAt(LocalDateTime.now());

        return mapToResponse(application);
    }

    // =========================================================
    // FIND APPLICATION
    // =========================================================

    private Application findApplication(
            Long applicationId) {

        return applications.stream()
                .filter(application ->
                        application.getApplicationId()
                                .equals(applicationId))
                .findFirst()
                .orElseThrow(() ->
                        new ApplicationNotFoundException(
                                "Application not found with id: "
                                        + applicationId
                        )
                );
    }

    // =========================================================
    // ENTITY -> RESPONSE
    // =========================================================

    private ApplicationResponse mapToResponse(
            Application application) {

        return new ApplicationResponse(
                application.getApplicationId(),
                application.getLeadId(),
                application.getCustomerId(),
                application.getApplicationNumber(),
                application.getLoanProduct(),
                application.getLoanAmount(),
                application.getLoanTenure(),
                application.getInterestRate(),
                application.getStatus(),
                application.getDescription(),
                application.getCreatedAt(),
                application.getModifiedAt()
        );
    }
}