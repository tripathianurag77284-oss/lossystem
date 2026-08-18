package com.los.documentservice.controller;

import com.los.documentservice.dto.DocumentTypeMasterRequest;
import com.los.documentservice.dto.DocumentTypeMasterResponse;
import com.los.documentservice.service.DocumentTypeMasterService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/document-types")
public class DocumentTypeMasterController {

    private final DocumentTypeMasterService documentTypeMasterService;

    public DocumentTypeMasterController(DocumentTypeMasterService documentTypeMasterService) {
        this.documentTypeMasterService = documentTypeMasterService;
    }

    @GetMapping
    public List<DocumentTypeMasterResponse> getAllDocumentTypes() {
        return documentTypeMasterService.getAllDocumentTypes();
    }

    @GetMapping("/{documentTypeId}")
    public DocumentTypeMasterResponse getDocumentTypeById(@PathVariable Long documentTypeId) {
        return documentTypeMasterService.getDocumentTypeById(documentTypeId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DocumentTypeMasterResponse createDocumentType(@Valid @RequestBody DocumentTypeMasterRequest request) {
        return documentTypeMasterService.createDocumentType(request);
    }

    @PutMapping("/{documentTypeId}")
    public DocumentTypeMasterResponse updateDocumentType(@PathVariable Long documentTypeId,
                                                       @Valid @RequestBody DocumentTypeMasterRequest request) {
        return documentTypeMasterService.updateDocumentType(documentTypeId, request);
    }

    @DeleteMapping("/{documentTypeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDocumentType(@PathVariable Long documentTypeId) {
        documentTypeMasterService.deleteDocumentType(documentTypeId);
    }
}
