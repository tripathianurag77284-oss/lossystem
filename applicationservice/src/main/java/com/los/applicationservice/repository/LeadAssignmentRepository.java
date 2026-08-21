package com.los.applicationservice.repository;

import com.los.applicationservice.model.LeadAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeadAssignmentRepository
        extends JpaRepository<LeadAssignment, Long> {

    List<LeadAssignment> findByLeadId(Long leadId);
}