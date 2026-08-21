package com.los.applicationservice.repository;

import com.los.applicationservice.model.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationRepository
        extends JpaRepository<Application, Long> {
}