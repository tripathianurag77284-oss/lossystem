package com.los.applicationservice.service.serviceImpl;

import com.los.applicationservice.dto.*;
import com.los.applicationservice.exception.ApplicationNotFoundException;
import com.los.applicationservice.kafka.KafkaProducerService;
import com.los.applicationservice.kafka.event.ApplicationCreatedEvent;
import com.los.applicationservice.kafka.event.ApplicationStatusChangedEvent;
import com.los.applicationservice.mock.*;
import com.los.applicationservice.model.*;
import com.los.applicationservice.service.ApplicationService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
    // GET ALL LOAN PRODUCTS
    // =========================================================

    @Override
    public List<LoanProductResponse> getAllLoanProducts() {

        return LoanProductMockData.getLoanProducts()
                .stream()
                .filter(product ->
                        Boolean.TRUE.equals(product.getIsActive())
                                && !Boolean.TRUE.equals(product.getIsDeleted()))
                .map(this::toResponse)
                .toList();
    }
    // =========================================================
    // GET LOAN PRODUCTS BY ID
    // =========================================================

    @Override
    public LoanProductResponse getLoanProductById(Long productId) {

        LoanProduct product =
                LoanProductMockData.getLoanProducts()
                        .stream()
                        .filter(p ->
                                p.getProductId().equals(productId))
                        .findFirst()
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Loan product not found: "
                                                + productId
                                ));

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


    @Override
    public List<TaskMasterResponse> getAllTasks() {
        return TaskMockData.getTasks() .stream()
                .map(this::mapToResponse) .toList();
    }

    @Override
    public TaskMasterResponse getTaskById(Long taskId) {
        TaskMaster task = TaskMockData.getTasks() .stream()
                .filter(t -> t.getTaskId().equals(taskId))
                .findFirst() .orElseThrow(() -> new RuntimeException( "Task not found: " + taskId ) );
        return mapToResponse(task);
    }

    @Override
    public List<TaskStageMasterResponse> getAllTaskStages() {
        return TaskStageMockData.getStages() .stream()
                .map(this::mapToResponse) .toList();
    }


    @Override
    public TaskStageMasterResponse getTaskStageById( Long taskStageId) {
        TaskStageMaster stage = TaskStageMockData.getStages() .stream()
                .filter(s -> s.getTaskStageId()
                        .equals(taskStageId)) .findFirst()
                .orElseThrow(() -> new RuntimeException( "Task stage not found: " + taskStageId ) );
        return mapToResponse(stage);
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

    @Override
    public List<ApplicationAssignmentResponse> getAssignmentsByApplicationId(Long applicationId) {
        return ApplicationAssignmentMockData.getAssignments()
                .stream() .filter(
                        a -> a.getApplicationId() .equals(applicationId))
                .map(this::mapToResponse) .toList();
    }

    @Override
    public ApplicationAssignmentResponse getAssignmentById(Long assignmentId) {
        ApplicationAssignment assignment = findAssignment(assignmentId);
        return mapToResponse(assignment);
    }

    @Override
    public ApplicationAssignmentResponse createAssignment( Long applicationId, ApplicationAssignmentRequest request) {
        long newId = ApplicationAssignmentMockData .getAssignments()
                .stream() .mapToLong(ApplicationAssignment::getAssignmentId)
                .max() .orElse(5000L) + 1; LocalDateTime now = LocalDateTime.now();
                ApplicationAssignment assignment = new ApplicationAssignment();
                assignment.setAssignmentId(newId);
                assignment.setApplicationId(applicationId);
                assignment.setAssignedForTaskId( request.getAssignedForTaskId() );
                assignment.setTaskStageId( request.getTaskStageId() );
                assignment.setAssignmentStatus( request.getAssignmentStatus() != null ? request.getAssignmentStatus() : "ASSIGNED" );
                assignment.setAssignmentRemark( request.getAssignmentRemark() );
                assignment.setProceedToUserId( request.getProceedToUserId() );
                assignment.setIsTerminal( request.getIsTerminal() != null ? request.getIsTerminal() : false );
                assignment.setTotalProgress( BigDecimal.ZERO );
                assignment.setIsActive(true);
                assignment.setCreatedAt(now);
                assignment.setModifiedAt(now);
                assignment.setCreatedById(101L);
                assignment.setModifiedById(101L);
                assignment.setIsDeleted(false);
                ApplicationAssignmentMockData .getAssignments()
                        .add(assignment); return mapToResponse(assignment);
    }

    @Override
    public ApplicationAssignmentResponse updateAssignmentStatus( Long assignmentId, AssignmentStatusUpdateRequest request) {
        ApplicationAssignment assignment = findAssignment(assignmentId);
        assignment.setAssignmentStatus( request.getStatus() );
        if (request.getRemark() != null) {
            assignment.setAssignmentRemark( request.getRemark() );
        }
        assignment.setModifiedAt(LocalDateTime.now());
        return mapToResponse(assignment);
    }

    private TaskMasterResponse mapToResponse(TaskMaster task) {
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
                task.getIsDeleted() );
    }

    private TaskStageMasterResponse mapToResponse( TaskStageMaster stage) {
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
                stage.getIsDeleted() );
    }

    private ApplicationAssignment findAssignment( Long assignmentId) {
        return ApplicationAssignmentMockData .getAssignments() .stream() .filter(
                a -> a.getAssignmentId() .equals(assignmentId))
                .findFirst() .orElseThrow(() -> new RuntimeException( "Application assignment not found: " + assignmentId ) );
    }

    private ApplicationAssignmentResponse mapToResponse(ApplicationAssignment assignment) {
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
                assignment.getIsDeleted() );
    }

}