package com.los.customerservice.repository;

import com.los.customerservice.model.ApplicationRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationRoleRepository
        extends JpaRepository<ApplicationRole, Long> {
}