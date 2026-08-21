package com.los.applicationservice.repository;

import com.los.applicationservice.model.TaskStageMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskStageMasterRepository
        extends JpaRepository<TaskStageMaster, Long> {
}