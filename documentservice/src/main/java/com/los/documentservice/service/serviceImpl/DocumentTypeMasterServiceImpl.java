package com.los.documentservice.service.serviceImpl;

import com.los.documentservice.dto.DocumentTypeMasterRequest;
import com.los.documentservice.dto.DocumentTypeMasterResponse;
import com.los.documentservice.mock.DocumentTypeMasterMockData;
import com.los.documentservice.model.DocumentTypeMaster;
import com.los.documentservice.service.DocumentTypeMasterService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class DocumentTypeMasterServiceImpl implements DocumentTypeMasterService {

    private final List<DocumentTypeMaster> documentTypes = new ArrayList<>();
    private final AtomicLong nextId = new AtomicLong(1);

    public DocumentTypeMasterServiceImpl() {
        DocumentTypeMasterMockData.getDocumentTypes().forEach(documentType -> {
            documentType.setDocumentTypeId(nextId.getAndIncrement());
            documentTypes.add(documentType);
        });
    }

    @Override
    public List<DocumentTypeMasterResponse> getAllDocumentTypes() {
        return documentTypes.stream()
                .filter(documentType -> !Boolean.TRUE.equals(documentType.getIsDeleted()))
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public DocumentTypeMasterResponse getDocumentTypeById(Long documentTypeId) {
        DocumentTypeMaster documentType = findDocumentType(documentTypeId);
        return mapToResponse(documentType);
    }

    @Override
    public synchronized DocumentTypeMasterResponse createDocumentType(DocumentTypeMasterRequest request) {
        DocumentTypeMaster documentType = new DocumentTypeMaster();
        documentType.setDocumentTypeId(nextId.getAndIncrement());
        documentType.setDocumentName(request.getDocumentName());
        documentType.setCategory(request.getCategory());
        documentType.setIsPoi(request.getIsPoi());
        documentType.setIsPoa(request.getIsPoa());
        documentType.setIsFacialDocument(request.getIsFacialDocument());
        documentType.setIsActive(request.getIsActive());
        documentType.setCreatedAt(LocalDateTime.now());
        documentType.setModifiedAt(LocalDateTime.now());
        documentType.setCreatedBy(request.getCreatedBy());
        documentType.setModifiedBy(request.getCreatedBy());
        documentType.setIsDeleted(false);

        documentTypes.add(documentType);
        return mapToResponse(documentType);
    }

    @Override
    public synchronized DocumentTypeMasterResponse updateDocumentType(Long documentTypeId, DocumentTypeMasterRequest request) {
        DocumentTypeMaster documentType = findDocumentType(documentTypeId);
        documentType.setDocumentName(request.getDocumentName());
        documentType.setCategory(request.getCategory());
        documentType.setIsPoi(request.getIsPoi());
        documentType.setIsPoa(request.getIsPoa());
        documentType.setIsFacialDocument(request.getIsFacialDocument());
        documentType.setIsActive(request.getIsActive());
        documentType.setModifiedAt(LocalDateTime.now());
        documentType.setModifiedBy(request.getModifiedBy() != null ? request.getModifiedBy() : request.getCreatedBy());
        return mapToResponse(documentType);
    }

    @Override
    public synchronized void deleteDocumentType(Long documentTypeId) {
        DocumentTypeMaster documentType = findDocumentType(documentTypeId);
        documentType.setIsDeleted(true);
        documentType.setIsActive(false);
        documentType.setModifiedAt(LocalDateTime.now());
    }

    private DocumentTypeMaster findDocumentType(Long documentTypeId) {
        return documentTypes.stream()
                .filter(documentType -> documentTypeId.equals(documentType.getDocumentTypeId()))
                .filter(documentType -> !Boolean.TRUE.equals(documentType.getIsDeleted()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Document type not found: " + documentTypeId));
    }

    private DocumentTypeMasterResponse mapToResponse(DocumentTypeMaster documentType) {
        return new DocumentTypeMasterResponse(
                documentType.getDocumentTypeId(),
                documentType.getDocumentName(),
                documentType.getCategory(),
                documentType.getIsPoi(),
                documentType.getIsPoa(),
                documentType.getIsFacialDocument(),
                documentType.getIsActive(),
                documentType.getCreatedAt(),
                documentType.getModifiedAt(),
                documentType.getCreatedBy(),
                documentType.getModifiedBy(),
                documentType.getIsDeleted()
        );
    }
}
