package com.los.documentservice.repository;

import com.los.documentservice.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DocumentRepository extends JpaRepository<Document, Long> {
    List<Document> findAllByIsDeletedFalse();
    Optional<Document> findByDocumentIdAndIsDeletedFalse(Long documentId);
}
