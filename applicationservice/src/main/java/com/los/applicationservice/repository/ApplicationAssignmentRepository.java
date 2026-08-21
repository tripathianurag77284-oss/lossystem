package com.los.applicationservice.repository;

import com.los.applicationservice.model.ApplicationAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationAssignmentRepository
        extends JpaRepository<ApplicationAssignment, Long> {

    List<ApplicationAssignment> findByApplicationId(Long applicationId);
}