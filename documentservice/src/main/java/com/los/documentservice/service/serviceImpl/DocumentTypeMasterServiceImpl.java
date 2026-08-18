package com.los.documentservice.service.serviceImpl;

import com.los.documentservice.dto.DocumentTypeMasterRequest;
import com.los.documentservice.dto.DocumentTypeMasterResponse;
import com.los.documentservice.exception.DocumentNotFoundException;
import com.los.documentservice.model.DocumentTypeMaster;
import com.los.documentservice.repository.DocumentTypeMasterRepository;
import com.los.documentservice.service.DocumentTypeMasterService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class DocumentTypeMasterServiceImpl implements DocumentTypeMasterService {

    private final DocumentTypeMasterRepository documentTypeRepository;

    public DocumentTypeMasterServiceImpl(DocumentTypeMasterRepository documentTypeRepository) {
        this.documentTypeRepository = documentTypeRepository;
    }

    @Override
    public List<DocumentTypeMasterResponse> getAllDocumentTypes() {
        return documentTypeRepository.findAllByIsDeletedFalse().stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public DocumentTypeMasterResponse getDocumentTypeById(Long documentTypeId) {
        DocumentTypeMaster documentType = findDocumentType(documentTypeId);
        return mapToResponse(documentType);
    }

    @Override
    @Transactional
    public DocumentTypeMasterResponse createDocumentType(DocumentTypeMasterRequest request) {
        DocumentTypeMaster documentType = new DocumentTypeMaster();
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

        return mapToResponse(documentTypeRepository.save(documentType));
    }

    @Override
    @Transactional
    public DocumentTypeMasterResponse updateDocumentType(Long documentTypeId, DocumentTypeMasterRequest request) {
        DocumentTypeMaster documentType = findDocumentType(documentTypeId);
        documentType.setDocumentName(request.getDocumentName());
        documentType.setCategory(request.getCategory());
        documentType.setIsPoi(request.getIsPoi());
        documentType.setIsPoa(request.getIsPoa());
        documentType.setIsFacialDocument(request.getIsFacialDocument());
        documentType.setIsActive(request.getIsActive());
        documentType.setModifiedAt(LocalDateTime.now());
        documentType.setModifiedBy(request.getModifiedBy() != null ? request.getModifiedBy() : request.getCreatedBy());
        return mapToResponse(documentTypeRepository.save(documentType));
    }

    @Override
    @Transactional
    public void deleteDocumentType(Long documentTypeId) {
        DocumentTypeMaster documentType = findDocumentType(documentTypeId);
        documentType.setIsDeleted(true);
        documentType.setIsActive(false);
        documentType.setModifiedAt(LocalDateTime.now());
        documentTypeRepository.save(documentType);
    }

    private DocumentTypeMaster findDocumentType(Long documentTypeId) {
        return documentTypeRepository.findByDocumentTypeIdAndIsDeletedFalse(documentTypeId)
                .orElseThrow(() -> new DocumentNotFoundException("Document type", documentTypeId));
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
