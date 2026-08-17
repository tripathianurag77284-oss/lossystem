package com.los.salesservice.service;

import com.los.salesservice.dto.ApplicationStatusUpdateRequest;
import com.los.salesservice.dto.LeadRequest;
import com.los.salesservice.dto.LeadResponse;
import com.los.salesservice.dto.LeadTrackingResponse;

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
}