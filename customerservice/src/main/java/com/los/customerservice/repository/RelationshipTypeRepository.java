package com.los.customerservice.repository;

import com.los.customerservice.model.RelationshipType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RelationshipTypeRepository
        extends JpaRepository<RelationshipType, Long> {
}