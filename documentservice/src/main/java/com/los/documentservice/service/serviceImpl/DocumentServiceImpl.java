package com.los.documentservice.service.serviceImpl;

import com.los.documentservice.dto.DocumentRequest;
import com.los.documentservice.dto.DocumentResponse;
import com.los.documentservice.exception.DocumentNotFoundException;
import com.los.documentservice.model.Document;
import com.los.documentservice.repository.DocumentRepository;
import com.los.documentservice.service.DocumentService;
import com.los.documentservice.service.FileStorageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional(readOnly = true)
public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepository documentRepository;
    private final FileStorageService fileStorageService;

    public DocumentServiceImpl(DocumentRepository documentRepository, FileStorageService fileStorageService) {
        this.documentRepository = documentRepository;
        this.fileStorageService = fileStorageService;
    }

    @Override
    public List<DocumentResponse> getAllDocuments() {
        return documentRepository.findAllByIsDeletedFalse().stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public DocumentResponse getDocumentById(Long documentId) {
        Document document = findDocument(documentId);
        return mapToResponse(document);
    }

    @Override
    @Transactional
    public DocumentResponse createDocument(
            DocumentRequest request,
            MultipartFile file) {

        String storedPath = fileStorageService.store(file);

        try {
            Document document = new Document();

            // Request data
            document.setApplicantId(request.getApplicantId());
            document.setDocumentTypeId(request.getDocumentTypeId());
            document.setUploadStage(request.getUploadStage());

            // File information
            document.setFileUrl(storedPath);

            // Verification information
            document.setVerifiedFlag(
                    Boolean.TRUE.equals(request.getVerifiedFlag())
            );

            document.setVerificationMethod(
                    request.getVerificationMethod()
            );

            document.setVerificationRemark(
                    request.getVerificationRemark()
            );

            document.setVerificationStatus(
                    request.getVerificationStatus()
            );

            document.setVerificationMode(
                    request.getVerificationMode()
            );

            // Active / deleted flags
            document.setIsActive(
                    request.getIsActive() != null
                            ? request.getIsActive()
                            : true
            );

            document.setIsDeleted(false);

            // Audit fields
            LocalDateTime now = LocalDateTime.now();

            document.setCreatedAt(now);
            document.setModifiedAt(now);

            document.setCreatedBy(
                    request.getCreatedBy()
            );

            document.setModifiedBy(
                    request.getModifiedBy()
            );

            // Verification audit
            if (Boolean.TRUE.equals(request.getVerifiedFlag())) {
                document.setVerifiedAt(now);
                document.setVerifiedBy(
                        request.getVerifiedBy()
                );
            } else {
                document.setVerifiedAt(null);
                document.setVerifiedBy(null);
            }

            // Save document
            Document savedDocument =
                    documentRepository.save(document);

            return mapToResponse(savedDocument);

        } catch (RuntimeException exception) {

            // If DB save fails, remove uploaded file
            fileStorageService.delete(storedPath);

            throw exception;
        }
    }
    @Override
    @Transactional
    public DocumentResponse updateDocument(Long documentId, DocumentRequest request, MultipartFile file) {
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
        try {
            DocumentResponse response = mapToResponse(documentRepository.save(document));
            fileStorageService.delete(previousPath);
            return response;
        } catch (RuntimeException exception) {
            fileStorageService.delete(storedPath);
            throw exception;
        }
    }

    @Override
    @Transactional
    public void deleteDocument(Long documentId) {
        Document document = findDocument(documentId);
        fileStorageService.delete(document.getFileUrl());
        document.setFileUrl(null);
        document.setIsDeleted(true);
        document.setIsActive(false);
        document.setModifiedAt(LocalDateTime.now());
        documentRepository.save(document);
    }

    private Document findDocument(Long documentId) {
        return documentRepository.findByDocumentIdAndIsDeletedFalse(documentId)
                .orElseThrow(() -> new DocumentNotFoundException(documentId));
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
