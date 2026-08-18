package com.los.applicationservice.service;

import com.los.applicationservice.dto.*;

import java.util.List;

public interface LeadService {

    List<LeadResponse> getAllLeads();

    LeadResponse getLeadById(Long leadId);

    LeadResponse createLead(LeadRequest request);

    LeadResponse updateLead(
            Long leadId,
            LeadRequest request
    );

    void deleteLead(Long leadId);

    LeadResponse updateLeadStatus(
            Long leadId,
            ApplicationStatusUpdateRequest request
    );

    List<LeadTrackingResponse> getLeadTracking(
            Long leadId
    );

    List<LeadAssignmentResponse> getAllAssignments();

    List<LeadAssignmentResponse> getAssignmentsByLeadId(Long leadId);

    LeadAssignmentResponse getAssignmentById(Long assignmentId);

    LeadAssignmentResponse createAssignment(
            Long leadId,
            LeadAssignmentRequest request
    );

    LeadAssignmentResponse updateAssignment(
            Long assignmentId,
            LeadAssignmentRequest request
    );

    void deleteAssignment(Long assignmentId);
}