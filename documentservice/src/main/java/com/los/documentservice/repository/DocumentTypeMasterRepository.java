package com.los.documentservice.repository;

import com.los.documentservice.model.DocumentTypeMaster;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DocumentTypeMasterRepository extends JpaRepository<DocumentTypeMaster, Long> {
    List<DocumentTypeMaster> findAllByIsDeletedFalse();
    Optional<DocumentTypeMaster> findByDocumentTypeIdAndIsDeletedFalse(Long documentTypeId);
}
