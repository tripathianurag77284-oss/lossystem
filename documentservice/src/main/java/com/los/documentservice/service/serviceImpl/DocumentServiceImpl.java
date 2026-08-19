package com.los.documentservice.service.serviceImpl;

import com.los.documentservice.dto.DocumentRequest;
import com.los.documentservice.dto.DocumentResponse;
import com.los.documentservice.mock.DocumentMockData;
import com.los.documentservice.model.Document;
import com.los.documentservice.service.DocumentService;
import com.los.documentservice.service.FileStorageService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.multipart.MultipartFile;

@Service
public class DocumentServiceImpl implements DocumentService {

    private final List<Document> documents = new ArrayList<>();
    private final AtomicLong nextId = new AtomicLong(1);
    private final FileStorageService fileStorageService;

    public DocumentServiceImpl(FileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
        DocumentMockData.getDocuments().forEach(document -> {
            document.setDocumentId(nextId.getAndIncrement());
            documents.add(document);
        });
    }

    @Override
    public List<DocumentResponse> getAllDocuments() {
        return documents.stream()
                .filter(document -> !Boolean.TRUE.equals(document.getIsDeleted()))
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public DocumentResponse getDocumentById(Long documentId) {
        Document document = findDocument(documentId);
        return mapToResponse(document);
    }

    @Override
    public synchronized DocumentResponse createDocument(DocumentRequest request, MultipartFile file) {
        String storedPath = fileStorageService.store(file);
        Document document = new Document();
        document.setDocumentId(nextId.getAndIncrement());
        document.setApplicantId(request.getApplicantId());
        document.setDocumentTypeId(request.getDocumentTypeId());
        document.setUploadStage(request.getUploadStage());
        document.setFileUrl(storedPath);
        document.setVerifiedFlag(request.getVerifiedFlag());
        document.setVerificationRemark(request.getVerificationRemark());
        document.setVerificationStatus(request.getVerificationStatus());
        document.setVerificationMode(request.getVerificationMode());
        document.setValidatedFlag(request.getValidatedFlag());
        document.setValidationRemark(request.getValidationRemark());
        document.setValidationStatus(request.getValidationStatus());
        document.setValidationMode(request.getValidationMode());
        document.setIsActive(request.getIsActive());
        document.setCreatedAt(LocalDateTime.now());
        document.setModifiedAt(LocalDateTime.now());
        document.setVerifiedAt(Boolean.TRUE.equals(request.getVerifiedFlag()) ? LocalDateTime.now() : null);
        document.setValidatedAt(Boolean.TRUE.equals(request.getValidatedFlag()) ? LocalDateTime.now() : null);
        document.setCreatedBy(request.getCreatedBy());
        document.setModifiedBy(request.getModifiedBy());
        document.setVerifiedBy(request.getVerifiedBy());
        document.setValidatedBy(request.getValidatedBy());
        document.setIsDeleted(false);

        documents.add(document);
        return mapToResponse(document);
    }

    @Override
    public synchronized DocumentResponse updateDocument(Long documentId, DocumentRequest request, MultipartFile file) {
        Document document = findDocument(documentId);
        String previousPath = document.getFileUrl();
        String storedPath = fileStorageService.store(file);
        document.setApplicantId(request.getApplicantId());
        document.setDocumentTypeId(request.getDocumentTypeId());
        document.setUploadStage(request.getUploadStage());
        document.setFileUrl(storedPath);
        document.setVerifiedFlag(request.getVerifiedFlag());
        document.setVerificationRemark(request.getVerificationRemark());
        document.setVerificationStatus(request.getVerificationStatus());
        document.setVerificationMode(request.getVerificationMode());
        document.setValidatedFlag(request.getValidatedFlag());
        document.setValidationRemark(request.getValidationRemark());
        document.setValidationStatus(request.getValidationStatus());
        document.setValidationMode(request.getValidationMode());
        document.setIsActive(request.getIsActive());
        document.setModifiedAt(LocalDateTime.now());
        document.setModifiedBy(request.getModifiedBy());
        if (Boolean.TRUE.equals(request.getVerifiedFlag())) {
            if (document.getVerifiedAt() == null) {
                document.setVerifiedAt(LocalDateTime.now());
            }
            document.setVerifiedBy(request.getVerifiedBy());
        } else {
            document.setVerifiedAt(null);
            document.setVerifiedBy(null);
        }
        if (Boolean.TRUE.equals(request.getValidatedFlag())) {
            if (document.getValidatedAt() == null) {
                document.setValidatedAt(LocalDateTime.now());
            }
            document.setValidatedBy(request.getValidatedBy());
        } else {
            document.setValidatedAt(null);
            document.setValidatedBy(null);
        }
        fileStorageService.delete(previousPath);
        return mapToResponse(document);
    }

    @Override
    public synchronized void deleteDocument(Long documentId) {
        Document document = findDocument(documentId);
        fileStorageService.delete(document.getFileUrl());
        document.setFileUrl(null);
        document.setIsDeleted(true);
        document.setIsActive(false);
        document.setModifiedAt(LocalDateTime.now());
    }

    private Document findDocument(Long documentId) {
        return documents.stream()
                .filter(document -> documentId.equals(document.getDocumentId()))
                .filter(document -> !Boolean.TRUE.equals(document.getIsDeleted()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Document not found: " + documentId));
    }

    private DocumentResponse mapToResponse(Document document) {
        return new DocumentResponse(
                document.getDocumentId(),
                document.getApplicantId(),
                document.getDocumentTypeId(),
                document.getUploadStage(),
                document.getFileUrl(),
                document.getVerifiedFlag(),
                document.getVerificationRemark(),
                document.getVerificationStatus(),
                document.getVerificationMode(),
                document.getValidatedFlag(),
                document.getValidationRemark(),
                document.getValidationStatus(),
                document.getValidationMode(),
                document.getIsActive(),
                document.getCreatedAt(),
                document.getModifiedAt(),
                document.getVerifiedAt(),
                document.getValidatedAt(),
                document.getCreatedBy(),
                document.getModifiedBy(),
                document.getVerifiedBy(),
                document.getValidatedBy(),
                document.getIsDeleted()
        );
    }
}
