package com.los.documentservice.service;

import com.los.documentservice.dto.DocumentRequest;
import com.los.documentservice.dto.DocumentResponse;

import java.util.List;

public interface DocumentService {

    List<DocumentResponse> getAllDocuments();

    DocumentResponse getDocumentById(Long documentId);

    DocumentResponse createDocument(DocumentRequest request);

    DocumentResponse updateDocument(Long documentId, DocumentRequest request);

    void deleteDocument(Long documentId);
}
