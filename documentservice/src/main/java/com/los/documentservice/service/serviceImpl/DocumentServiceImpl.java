package com.los.documentservice.service.serviceImpl;

import com.los.documentservice.dto.DocumentRequest;
import com.los.documentservice.dto.DocumentResponse;
import com.los.documentservice.exception.DocumentNotFoundException;
import com.los.documentservice.model.Document;
import com.los.documentservice.repository.DocumentRepository;
import com.los.documentservice.service.DocumentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepository documentRepository;

    public DocumentServiceImpl(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
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
    public DocumentResponse createDocument(DocumentRequest request) {
        Document document = new Document();
        document.setApplicantId(request.getApplicantId());
        document.setDocumentTypeId(request.getDocumentTypeId());
        document.setUploadStage(request.getUploadStage());
        document.setFileUrl(request.getFileUrl());
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

        return mapToResponse(documentRepository.save(document));
    }

    @Override
    @Transactional
    public DocumentResponse updateDocument(Long documentId, DocumentRequest request) {
        Document document = findDocument(documentId);
        document.setApplicantId(request.getApplicantId());
        document.setDocumentTypeId(request.getDocumentTypeId());
        document.setUploadStage(request.getUploadStage());
        document.setFileUrl(request.getFileUrl());
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
        return mapToResponse(documentRepository.save(document));
    }

    @Override
    @Transactional
    public void deleteDocument(Long documentId) {
        Document document = findDocument(documentId);
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
