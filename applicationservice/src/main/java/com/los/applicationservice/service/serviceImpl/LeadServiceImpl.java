package com.los.applicationservice.service.serviceImpl;


import com.los.applicationservice.dto.*;
import com.los.applicationservice.exception.ApplicationNotFoundException;
import com.los.applicationservice.kafka.KafkaProducerService;
import com.los.applicationservice.kafka.event.LeadCreatedEvent;

import com.los.applicationservice.mock.LeadAssignmentMockData;
import com.los.applicationservice.mock.LeadMockData;
import com.los.applicationservice.model.ApplicationStatus;
import com.los.applicationservice.model.Lead;
import com.los.applicationservice.model.LeadAssignment;
import com.los.applicationservice.service.LeadService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LeadServiceImpl implements LeadService {

    /*
     * =========================================================
     * MOCK LEAD DATA
     * =========================================================
     *
     * Later replace this with LeadRepository + PostgreSQL.
     */

    private final List<Lead> leads =
            LeadMockData.getLeads();


    /*
     * =========================================================
     * MOCK TRACKING DATA
     * =========================================================
     */

    private final Map<Long, List<LeadTrackingResponse>>
            trackingData = new HashMap<>();


    /*
     * =========================================================
     * KAFKA PRODUCER
     * =========================================================
     */

    private final KafkaProducerService kafkaProducerService;


    @Autowired
    public LeadServiceImpl(
            KafkaProducerService kafkaProducerService) {

        this.kafkaProducerService = kafkaProducerService;
    }


    // =========================================================
    // GET ALL LEADS
    // =========================================================

    @Override
    public List<LeadResponse> getAllLeads() {

        return leads.stream()
                .map(this::mapToResponse)
                .toList();
    }


    // =========================================================
    // GET LEAD BY ID
    // =========================================================

    @Override
    public LeadResponse getLeadById(
            Long leadId) {

        Lead lead = findLead(leadId);

        return mapToResponse(lead);
    }


    // =========================================================
    // CREATE LEAD
    // =========================================================

    @Override
    public LeadResponse createLead(
            LeadRequest request) {

        /*
         * Generate mock ID.
         */

        long newId = leads.stream()
                .mapToLong(Lead::getLeadId)
                .max()
                .orElse(1000L) + 1;


        /*
         * Create Lead object.
         */

        Lead lead = new Lead();

        lead.setLeadId(newId);


        // =====================================================
        // REQUEST DATA
        // =====================================================

        lead.setSourceName(
                request.getSourceName()
        );

        lead.setChannelType(
                request.getChannelType()
        );

        lead.setMobile(
                request.getMobile()
        );

        lead.setPan(
                request.getPan()
        );

        lead.setDob(
                request.getDob()
        );

        lead.setDescription(
                request.getDescription()
        );


        // =====================================================
        // INITIAL STATUS
        // =====================================================

        lead.setStatus(
                ApplicationStatus.NEW
        );


        // =====================================================
        // SYSTEM FIELDS
        // =====================================================

        lead.setIsActive(true);

        lead.setIsDeleted(false);

        LocalDateTime now =
                LocalDateTime.now();

        lead.setCreatedAt(now);

        lead.setModifiedAt(now);

        /*
         * Mock logged-in user.
         */

        lead.setCreatedById(101L);

        lead.setModifiedById(101L);


        // =====================================================
        // SAVE TO MOCK LIST
        // =====================================================

        leads.add(lead);


        // =====================================================
        // TRACKING
        // =====================================================

        addTracking(
                newId,
                ApplicationStatus.NEW,
                "Lead created"
        );


        // =====================================================
        // KAFKA EVENT
        // =====================================================

        LeadCreatedEvent event =
                new LeadCreatedEvent(
                        lead.getLeadId(),
                        lead.getSourceName(),
                        lead.getChannelType(),
                        lead.getMobile(),
                        lead.getPan(),
                        lead.getDob(),
                        lead.getStatus(),
                        lead.getDescription()
                );


        kafkaProducerService.sendLeadCreated(
                lead.getLeadId(),
                event
        );


        // =====================================================
        // RESPONSE
        // =====================================================

        return mapToResponse(lead);
    }


    // =========================================================
    // UPDATE LEAD
    // =========================================================

    @Override
    public LeadResponse updateLead(
            Long leadId,
            LeadRequest request) {

        Lead existingLead =
                findLead(leadId);


        // =====================================================
        // UPDATE BUSINESS FIELDS
        // =====================================================

        existingLead.setSourceName(
                request.getSourceName()
        );

        existingLead.setChannelType(
                request.getChannelType()
        );

        existingLead.setMobile(
                request.getMobile()
        );

        existingLead.setPan(
                request.getPan()
        );

        existingLead.setDob(
                request.getDob()
        );

        existingLead.setDescription(
                request.getDescription()
        );


        // =====================================================
        // UPDATE SYSTEM FIELDS
        // =====================================================

        existingLead.setModifiedAt(
                LocalDateTime.now()
        );

        existingLead.setModifiedById(
                101L
        );


        return mapToResponse(
                existingLead
        );
    }


    // =========================================================
    // DELETE LEAD
    // =========================================================

    @Override
    public void deleteLead(
            Long leadId) {

        Lead existingLead =
                findLead(leadId);

        leads.remove(existingLead);

        trackingData.remove(leadId);
    }


    // =========================================================
    // UPDATE LEAD STATUS
    // =========================================================

    @Override
    public LeadResponse updateLeadStatus(
            Long leadId,
            ApplicationStatusUpdateRequest request) {

        Lead lead =
                findLead(leadId);


        ApplicationStatus oldStatus =
                lead.getStatus();


        ApplicationStatus newStatus =
                request.getStatus();


        // =====================================================
        // VALIDATE STATUS
        // =====================================================

        validateStatusTransition(
                oldStatus,
                newStatus
        );


        // =====================================================
        // UPDATE STATUS
        // =====================================================

        lead.setStatus(
                newStatus
        );


        // =====================================================
        // UPDATE SYSTEM DATA
        // =====================================================

        lead.setModifiedAt(
                LocalDateTime.now()
        );

        lead.setModifiedById(
                101L
        );


        // =====================================================
        // TRACKING
        // =====================================================

        String remarks =
                "Lead status changed from "
                        + oldStatus
                        + " to "
                        + newStatus;


        addTracking(
                leadId,
                newStatus,
                remarks
        );


        // =====================================================
        // KAFKA STATUS EVENT
        // =====================================================

        LeadCreatedEvent event =
                new LeadCreatedEvent(
                        lead.getLeadId(),
                        lead.getSourceName(),
                        lead.getChannelType(),
                        lead.getMobile(),
                        lead.getPan(),
                        lead.getDob(),
                        lead.getStatus(),
                        lead.getDescription()
                );


        kafkaProducerService
                .sendLeadCreated(
                        lead.getLeadId(),
                        event
                );


        return mapToResponse(
                lead
        );
    }


    // =========================================================
    // GET LEAD TRACKING
    // =========================================================

    @Override
    public List<LeadTrackingResponse> getLeadTracking(
            Long leadId) {

        /*
         * Make sure lead exists.
         */

        findLead(leadId);


        return trackingData.getOrDefault(
                leadId,
                new ArrayList<>()
        );
    }


    // =========================================================
    // FIND LEAD
    // =========================================================

    private Lead findLead(
            Long leadId) {

        return leads.stream()

                .filter(
                        lead ->
                                lead.getLeadId()
                                        .equals(leadId)
                )

                .findFirst()

                .orElseThrow(
                        () ->
                                new ApplicationNotFoundException(
                                        "Lead not found with id: "
                                                + leadId
                                )
                );
    }


    // =========================================================
    // ADD TRACKING
    // =========================================================

    private void addTracking(
            Long leadId,
            ApplicationStatus status,
            String remarks) {

        LeadTrackingResponse tracking =
                new LeadTrackingResponse(
                        leadId,
                        status,
                        LocalDateTime.now(),
                        remarks
                );


        trackingData

                .computeIfAbsent(
                        leadId,
                        key ->
                                new ArrayList<>()
                )

                .add(tracking);
    }


    // =========================================================
    // STATUS TRANSITION VALIDATION
    // =========================================================

    private void validateStatusTransition(
            ApplicationStatus oldStatus,
            ApplicationStatus newStatus) {

        /*
         * Same status is not allowed.
         */

        if (oldStatus == newStatus) {

            throw new IllegalArgumentException(
                    "Lead is already in status: "
                            + newStatus
            );
        }


        /*
         * For now all different transitions
         * are allowed.
         */
    }


    // =========================================================
    // ENTITY -> RESPONSE
    // =========================================================

    private LeadResponse mapToResponse(
            Lead lead) {

        return new LeadResponse(

                lead.getLeadId(),

                lead.getSourceName(),

                lead.getChannelType(),

                lead.getMobile(),

                lead.getPan(),

                lead.getDob(),

                lead.getStatus(),

                lead.getDescription(),

                lead.getIsActive(),

                lead.getCreatedAt(),

                lead.getModifiedAt(),

                lead.getCreatedById(),

                lead.getModifiedById(),

                lead.getIsDeleted()
        );
    }

    // =========================================================
    // GET ALL ASSIGNMENTS
    // =========================================================

    @Override
    public List<LeadAssignmentResponse> getAllAssignments() {

        return LeadAssignmentMockData.getAssignments()
                .stream()
                .map(this::toResponse)
                .toList();
    }


    // =========================================================
    // GET ASSIGNMENTS BY LEAD ID
    // =========================================================

    @Override
    public List<LeadAssignmentResponse> getAssignmentsByLeadId(
            Long leadId) {

        return LeadAssignmentMockData.getAssignments()
                .stream()
                .filter(assignment ->
                        assignment.getLeadId().equals(leadId))
                .map(this::toResponse)
                .toList();
    }


    // =========================================================
    // GET ASSIGNMENT BY ID
    // =========================================================

    @Override
    public LeadAssignmentResponse getAssignmentById(
            Long assignmentId) {

        LeadAssignment assignment =
                LeadAssignmentMockData.getAssignments()
                        .stream()
                        .filter(a ->
                                a.getAssignmentId().equals(assignmentId))
                        .findFirst()
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Lead assignment not found: "
                                                + assignmentId
                                )
                        );

        return toResponse(assignment);
    }


    // =========================================================
    // CREATE ASSIGNMENT
    // =========================================================

    @Override
    public LeadAssignmentResponse createAssignment(
            Long leadId,
            LeadAssignmentRequest request) {

        LeadAssignment assignment = new LeadAssignment();

        assignment.setAssignmentId(
                generateAssignmentId()
        );

        assignment.setLeadId(leadId);

        assignment.setAssignedForTaskId(
                request.getAssignedForTaskId()
        );

        assignment.setAssignmentStatus(
                request.getAssignmentStatus()
        );

        assignment.setAssignmentRemark(
                request.getAssignmentRemark()
        );

        assignment.setProceedToId(
                request.getProceedToId()
        );

        assignment.setIsTerminal(
                request.getIsTerminal()
        );

        assignment.setIsActive(true);

        assignment.setCreatedAt(
                LocalDateTime.now()
        );

        assignment.setModifiedAt(
                LocalDateTime.now()
        );

        assignment.setIsDeleted(false);

        LeadAssignmentMockData
                .getAssignments()
                .add(assignment);

        return toResponse(assignment);
    }


    // =========================================================
    // UPDATE ASSIGNMENT
    // =========================================================

    @Override
    public LeadAssignmentResponse updateAssignment(
            Long assignmentId,
            LeadAssignmentRequest request) {

        LeadAssignment assignment =
                LeadAssignmentMockData.getAssignments()
                        .stream()
                        .filter(a ->
                                a.getAssignmentId().equals(assignmentId))
                        .findFirst()
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Lead assignment not found: "
                                                + assignmentId
                                )
                        );

        assignment.setAssignedForTaskId(
                request.getAssignedForTaskId()
        );

        assignment.setAssignmentStatus(
                request.getAssignmentStatus()
        );

        assignment.setAssignmentRemark(
                request.getAssignmentRemark()
        );

        assignment.setProceedToId(
                request.getProceedToId()
        );

        assignment.setIsTerminal(
                request.getIsTerminal()
        );

        assignment.setModifiedAt(
                LocalDateTime.now()
        );

        return toResponse(assignment);
    }


    // =========================================================
    // DELETE ASSIGNMENT
    // =========================================================

    @Override
    public void deleteAssignment(Long assignmentId) {

        LeadAssignment assignment =
                LeadAssignmentMockData.getAssignments()
                        .stream()
                        .filter(a ->
                                a.getAssignmentId().equals(assignmentId))
                        .findFirst()
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Lead assignment not found: "
                                                + assignmentId
                                )
                        );

        // Soft delete
        assignment.setIsDeleted(true);
        assignment.setIsActive(false);
        assignment.setModifiedAt(LocalDateTime.now());
    }


    // =========================================================
    // CONVERT MODEL → RESPONSE DTO
    // =========================================================

    private LeadAssignmentResponse toResponse(
            LeadAssignment assignment) {

        return new LeadAssignmentResponse(

                assignment.getAssignmentId(),

                assignment.getLeadId(),

                assignment.getAssignedForTaskId(),

                assignment.getAssignmentStatus(),

                assignment.getAssignmentRemark(),

                assignment.getProceedToId(),

                assignment.getIsTerminal(),

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


    // =========================================================
    // GENERATE MOCK ASSIGNMENT ID
    // =========================================================

    private Long generateAssignmentId() {

        return LeadAssignmentMockData
                .getAssignments()
                .stream()
                .mapToLong(LeadAssignment::getAssignmentId)
                .max()
                .orElse(5000L) + 1;
    }
}