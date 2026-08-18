package com.los.documentservice.service;

import com.los.documentservice.dto.DocumentTypeMasterRequest;
import com.los.documentservice.dto.DocumentTypeMasterResponse;

import java.util.List;

public interface DocumentTypeMasterService {

    List<DocumentTypeMasterResponse> getAllDocumentTypes();

    DocumentTypeMasterResponse getDocumentTypeById(Long documentTypeId);

    DocumentTypeMasterResponse createDocumentType(DocumentTypeMasterRequest request);

    DocumentTypeMasterResponse updateDocumentType(Long documentTypeId, DocumentTypeMasterRequest request);

    void deleteDocumentType(Long documentTypeId);
}
