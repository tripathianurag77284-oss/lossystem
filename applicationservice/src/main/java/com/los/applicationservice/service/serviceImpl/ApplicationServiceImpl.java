package com.los.applicationservice.service.serviceImpl;

import com.los.applicationservice.dto.*;
import com.los.applicationservice.exception.ApplicationNotFoundException;
import com.los.applicationservice.kafka.KafkaProducerService;
import com.los.applicationservice.kafka.event.ApplicationAssignmentCreatedEvent;
import com.los.applicationservice.kafka.event.ApplicationAssignmentStatusChangedEvent;
import com.los.applicationservice.kafka.event.ApplicationCreatedEvent;
import com.los.applicationservice.kafka.event.ApplicationStatusChangedEvent;
import com.los.applicationservice.mock.*;
import com.los.applicationservice.model.*;
import com.los.applicationservice.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ApplicationServiceImpl implements ApplicationService {

    private final List<Application> applications =
            ApplicationMockData.getApplications();

    private final KafkaProducerService kafkaProducerService;


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

        LocalDateTime now = LocalDateTime.now();

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

        application.setCreatedAt(now);
        application.setModifiedAt(now);

        // Add application to mock data
        applications.add(application);


        // =====================================================
        // KAFKA - APPLICATION CREATED
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

        kafkaProducerService.sendApplicationCreatedEvent(event);


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

        ApplicationStatus oldStatus =
                application.getStatus();

        ApplicationStatus newStatus =
                request.getStatus();

        // No status change
        if (oldStatus == newStatus) {
            return mapToResponse(application);
        }

        application.setStatus(newStatus);

        application.setModifiedAt(
                LocalDateTime.now()
        );


        // =====================================================
        // KAFKA - APPLICATION STATUS CHANGED
        // =====================================================

        ApplicationStatusChangedEvent event =
                new ApplicationStatusChangedEvent(
                        application.getApplicationId(),
                        application.getLeadId(),
                        oldStatus,
                        newStatus,
                        application.getModifiedAt()
                );

        kafkaProducerService.sendApplicationStatusChangedEvent(
                event
        );


        return mapToResponse(application);
    }


    // =========================================================
    // GET ALL LOAN PRODUCTS
    // =========================================================

    @Override
    public List<LoanProductResponse> getAllLoanProducts() {

        return LoanProductMockData.getLoanProducts()
                .stream()
                .filter(product ->
                        Boolean.TRUE.equals(product.getIsActive())
                                && !Boolean.TRUE.equals(
                                product.getIsDeleted()))
                .map(this::toResponse)
                .toList();
    }


    // =========================================================
    // GET LOAN PRODUCT BY ID
    // =========================================================

    @Override
    public LoanProductResponse getLoanProductById(
            Long productId) {

        LoanProduct product =
                LoanProductMockData.getLoanProducts()
                        .stream()
                        .filter(product1 ->
                                product1.getProductId()
                                        .equals(productId))
                        .findFirst()
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Loan product not found: "
                                                + productId
                                )
                        );

        return toResponse(product);
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
    public ApplicationResponse resumeApplication(
            Long applicationId) {

        Application application =
                findApplication(applicationId);

        ApplicationStatus oldStatus =
                application.getStatus();

        // Only DRAFT can be resumed
        if (oldStatus != ApplicationStatus.DRAFT) {

            throw new RuntimeException(
                    "Only DRAFT applications can be resumed"
            );
        }

        ApplicationStatus newStatus =
                ApplicationStatus.IN_PROGRESS;

        application.setStatus(newStatus);

        application.setModifiedAt(
                LocalDateTime.now()
        );


        // =====================================================
        // KAFKA - APPLICATION STATUS CHANGED
        // =====================================================

        ApplicationStatusChangedEvent event =
                new ApplicationStatusChangedEvent(
                        application.getApplicationId(),
                        application.getLeadId(),
                        oldStatus,
                        newStatus,
                        application.getModifiedAt()
                );

        kafkaProducerService.sendApplicationStatusChangedEvent(
                event
        );


        return mapToResponse(application);
    }


    // =========================================================
    // GET ALL TASKS
    // =========================================================

    @Override
    public List<TaskMasterResponse> getAllTasks() {

        return TaskMockData.getTasks()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }


    // =========================================================
    // GET TASK BY ID
    // =========================================================

    @Override
    public TaskMasterResponse getTaskById(
            Long taskId) {

        TaskMaster task =
                TaskMockData.getTasks()
                        .stream()
                        .filter(t ->
                                t.getTaskId()
                                        .equals(taskId))
                        .findFirst()
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Task not found: "
                                                + taskId
                                )
                        );

        return mapToResponse(task);
    }


    // =========================================================
    // GET ALL TASK STAGES
    // =========================================================

    @Override
    public List<TaskStageMasterResponse> getAllTaskStages() {

        return TaskStageMockData.getStages()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }


    // =========================================================
    // GET TASK STAGE BY ID
    // =========================================================

    @Override
    public TaskStageMasterResponse getTaskStageById(
            Long taskStageId) {

        TaskStageMaster stage =
                TaskStageMockData.getStages()
                        .stream()
                        .filter(s ->
                                s.getTaskStageId()
                                        .equals(taskStageId))
                        .findFirst()
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Task stage not found: "
                                                + taskStageId
                                )
                        );

        return mapToResponse(stage);
    }


    // =========================================================
    // GET ASSIGNMENTS BY APPLICATION ID
    // =========================================================

    @Override
    public List<ApplicationAssignmentResponse>
    getAssignmentsByApplicationId(
            Long applicationId) {

        // Verify application exists
        findApplication(applicationId);

        return ApplicationAssignmentMockData
                .getAssignments()
                .stream()
                .filter(assignment ->
                        assignment.getApplicationId()
                                .equals(applicationId))
                .map(this::mapToResponse)
                .toList();
    }


    // =========================================================
    // GET APPLICATION ASSIGNMENT BY ID
    // =========================================================

    @Override
    public ApplicationAssignmentResponse
    getAssignmentById(
            Long assignmentId) {

        ApplicationAssignment assignment =
                findAssignment(assignmentId);

        return mapToResponse(assignment);
    }


    // =========================================================
    // CREATE APPLICATION ASSIGNMENT
    // =========================================================

    @Override
    public ApplicationAssignmentResponse createAssignment(
            Long applicationId,
            ApplicationAssignmentRequest request) {

        // Verify application exists
        findApplication(applicationId);

        long newId =
                ApplicationAssignmentMockData
                        .getAssignments()
                        .stream()
                        .mapToLong(
                                ApplicationAssignment::getAssignmentId)
                        .max()
                        .orElse(5000L) + 1;

        LocalDateTime now =
                LocalDateTime.now();

        ApplicationAssignment assignment =
                new ApplicationAssignment();

        assignment.setAssignmentId(newId);

        assignment.setApplicationId(
                applicationId
        );

        assignment.setAssignedForTaskId(
                request.getAssignedForTaskId()
        );

        assignment.setTaskStageId(
                request.getTaskStageId()
        );

        assignment.setAssignmentStatus(
                request.getAssignmentStatus() != null
                        ? request.getAssignmentStatus()
                        : "ASSIGNED"
        );

        assignment.setAssignmentRemark(
                request.getAssignmentRemark()
        );

        assignment.setProceedToUserId(
                request.getProceedToUserId()
        );

        assignment.setIsTerminal(
                request.getIsTerminal() != null
                        ? request.getIsTerminal()
                        : false
        );

        assignment.setTotalProgress(
                BigDecimal.ZERO
        );

        assignment.setIsActive(true);
        assignment.setCreatedAt(now);
        assignment.setModifiedAt(now);

        assignment.setCreatedById(101L);
        assignment.setModifiedById(101L);

        assignment.setIsDeleted(false);


        // Add to mock data
        ApplicationAssignmentMockData
                .getAssignments()
                .add(assignment);


        // =====================================================
        // KAFKA - APPLICATION ASSIGNMENT CREATED
        // =====================================================

        ApplicationAssignmentCreatedEvent event =
                new ApplicationAssignmentCreatedEvent(
                        assignment.getAssignmentId(),
                        assignment.getApplicationId(),
                        assignment.getAssignedForTaskId(),
                        assignment.getTaskStageId(),
                        assignment.getAssignmentStatus(),
                        assignment.getProceedToUserId(),
                        assignment.getCreatedAt()
                );

        kafkaProducerService
                .sendApplicationAssignmentCreated(event);


        return mapToResponse(assignment);
    }


    // =========================================================
    // UPDATE APPLICATION ASSIGNMENT STATUS
    // =========================================================

    @Override
    public ApplicationAssignmentResponse
    updateAssignmentStatus(
            Long assignmentId,
            AssignmentStatusUpdateRequest request) {

        ApplicationAssignment assignment =
                findAssignment(assignmentId);

        String oldStatus =
                assignment.getAssignmentStatus();

        String newStatus =
                request.getStatus();


        // No status change
        if (Objects.equals(oldStatus, newStatus)) {

            if (request.getRemark() != null) {
                assignment.setAssignmentRemark(
                        request.getRemark()
                );
            }

            return mapToResponse(assignment);
        }


        assignment.setAssignmentStatus(newStatus);

        if (request.getRemark() != null) {
            assignment.setAssignmentRemark(
                    request.getRemark()
            );
        }

        assignment.setModifiedAt(
                LocalDateTime.now()
        );


        // =====================================================
        // KAFKA - ASSIGNMENT STATUS CHANGED
        // =====================================================

        ApplicationAssignmentStatusChangedEvent event =
                new ApplicationAssignmentStatusChangedEvent(
                        assignment.getAssignmentId(),
                        assignment.getApplicationId(),
                        oldStatus,
                        newStatus,
                        assignment.getModifiedAt()
                );

        kafkaProducerService
                .sendApplicationAssignmentStatusChanged(event);


        return mapToResponse(assignment);
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
    // FIND ASSIGNMENT
    // =========================================================

    private ApplicationAssignment findAssignment(
            Long assignmentId) {

        return ApplicationAssignmentMockData
                .getAssignments()
                .stream()
                .filter(assignment ->
                        assignment.getAssignmentId()
                                .equals(assignmentId))
                .findFirst()
                .orElseThrow(() ->
                        new RuntimeException(
                                "Application assignment not found: "
                                        + assignmentId
                        )
                );
    }


    // =========================================================
    // APPLICATION -> RESPONSE
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


    // =========================================================
    // LOAN PRODUCT -> RESPONSE
    // =========================================================

    private LoanProductResponse toResponse(
            LoanProduct product) {

        return new LoanProductResponse(
                product.getProductId(),
                product.getProductName(),
                product.getProductScheme(),
                product.getProductSchemeCode(),
                product.getSubProductId(),
                product.getLoanCategory(),
                product.getMinAmount(),
                product.getMaxAmount(),
                product.getMinTenure(),
                product.getMaxTenure(),
                product.getIsActive()
        );
    }


    // =========================================================
    // TASK -> RESPONSE
    // =========================================================

    private TaskMasterResponse mapToResponse(
            TaskMaster task) {

        return new TaskMasterResponse(
                task.getTaskId(),
                task.getTaskName(),
                task.getTaskDescription(),
                task.getProgress(),
                task.getDeadline(),
                task.getExpectedSubmission(),
                task.getIsActive(),
                task.getCreatedAt(),
                task.getModifiedAt(),
                task.getCreatedById(),
                task.getModifiedById(),
                task.getIsDeleted()
        );
    }


    // =========================================================
    // TASK STAGE -> RESPONSE
    // =========================================================

    private TaskStageMasterResponse mapToResponse(
            TaskStageMaster stage) {

        return new TaskStageMasterResponse(
                stage.getTaskStageId(),
                stage.getStageName(),
                stage.getProgress(),
                stage.getDeadline(),
                stage.getExpectedSubmission(),
                stage.getIsActive(),
                stage.getCreatedAt(),
                stage.getModifiedAt(),
                stage.getCreatedById(),
                stage.getModifiedById(),
                stage.getIsDeleted()
        );
    }


    // =========================================================
    // APPLICATION ASSIGNMENT -> RESPONSE
    // =========================================================

    private ApplicationAssignmentResponse mapToResponse(
            ApplicationAssignment assignment) {

        return new ApplicationAssignmentResponse(
                assignment.getAssignmentId(),
                assignment.getApplicationId(),
                assignment.getAssignedForTaskId(),
                assignment.getTaskStageId(),
                assignment.getAssignmentStatus(),
                assignment.getAssignmentRemark(),
                assignment.getProceedToUserId(),
                assignment.getIsTerminal(),
                assignment.getTotalProgress(),
                assignment.getIsActive(),
                assignment.getCreatedAt(),
                assignment.getModifiedAt(),
                assignment.getVerifiedAt(),
                assignment.getCreatedById(),
                assignment.getModifiedById(),
                assignment.getVerifiedById(),
                assignment.getVerificationMode(),
                assignment.getIsDeleted()
        );
    }
}