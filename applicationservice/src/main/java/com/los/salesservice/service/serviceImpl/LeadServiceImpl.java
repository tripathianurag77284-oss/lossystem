package com.los.salesservice.service.serviceImpl;


import com.los.salesservice.dto.ApplicationStatusUpdateRequest;
import com.los.salesservice.dto.LeadRequest;
import com.los.salesservice.dto.LeadResponse;
import com.los.salesservice.dto.LeadTrackingResponse;
import com.los.salesservice.exception.ApplicationNotFoundException;
import com.los.salesservice.kafka.KafkaProducerService;
import com.los.salesservice.kafka.event.LeadCreatedEvent;

import com.los.salesservice.kafka.event.LeadStatusChangedEvent;
import com.los.salesservice.mock.LeadMockData;
import com.los.salesservice.model.ApplicationStatus;
import com.los.salesservice.model.Lead;
import com.los.salesservice.service.LeadService;

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


        kafkaProducerService.sendLeadCreatedEvent(
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
                .sendLeadCreatedEvent(
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
}