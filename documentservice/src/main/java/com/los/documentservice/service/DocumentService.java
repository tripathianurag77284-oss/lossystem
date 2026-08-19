package com.los.documentservice.service;

import com.los.documentservice.dto.DocumentRequest;
import com.los.documentservice.dto.DocumentResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface DocumentService {

    List<DocumentResponse> getAllDocuments();

    DocumentResponse getDocumentById(Long documentId);

    DocumentResponse createDocument(DocumentRequest request, MultipartFile file);

    DocumentResponse updateDocument(Long documentId, DocumentRequest request, MultipartFile file);

    void deleteDocument(Long documentId);
}
