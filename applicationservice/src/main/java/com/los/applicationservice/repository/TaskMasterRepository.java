package com.los.applicationservice.repository;

import com.los.applicationservice.model.TaskMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskMasterRepository
        extends JpaRepository<TaskMaster, Long> {
}