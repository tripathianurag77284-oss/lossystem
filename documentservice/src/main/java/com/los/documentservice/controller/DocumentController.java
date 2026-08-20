package com.los.documentservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.los.documentservice.dto.DocumentRequest;
import com.los.documentservice.dto.DocumentResponse;
import com.los.documentservice.dto.DocumentTypeMasterRequest;
import com.los.documentservice.dto.DocumentTypeMasterResponse;
import com.los.documentservice.service.DocumentService;
import com.los.documentservice.service.DocumentTypeMasterService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
public class DocumentController {

    private final DocumentService documentService;
    private final DocumentTypeMasterService documentTypeMasterService;



    public DocumentController(
            DocumentService documentService) {

        this.documentService = documentService;
        this.documentTypeMasterService = documentTypeMasterService;
    }
    @GetMapping
    public List<DocumentResponse> getAllDocuments() {
        return documentService.getAllDocuments();
    }

    @GetMapping("/api/documents/{documentId}")
    public DocumentResponse getDocumentById(@PathVariable Long documentId) {
        return documentService.getDocumentById(documentId);
    }

    @PostMapping(
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    @ResponseStatus(HttpStatus.CREATED)
    public DocumentResponse createDocument(
            @RequestPart("document") String documentJson,
            @RequestPart("file") MultipartFile file)
            throws JsonProcessingException {

        DocumentRequest request =
                new ObjectMapper().readValue(
                        documentJson,
                        DocumentRequest.class
                );

        return documentService.createDocument(
                request,
                file
        );
    }
    @PutMapping(value = "/{documentId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public DocumentResponse updateDocument(@PathVariable Long documentId,
                                           @Valid @RequestPart("document") DocumentRequest request,
                                           @RequestPart("file") MultipartFile file) {
        return documentService.updateDocument(documentId, request, file);
    }

    @DeleteMapping("/api/documents/{documentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDocument(@PathVariable Long documentId) {
        documentService.deleteDocument(documentId);
    }

    @GetMapping("/api/document-types")
    public List<DocumentTypeMasterResponse> getAllDocumentTypes() {
        return documentTypeMasterService.getAllDocumentTypes();
    }

    @GetMapping("/api/document-types/{documentTypeId}")
    public DocumentTypeMasterResponse getDocumentTypeById(@PathVariable Long documentTypeId) {
        return documentTypeMasterService.getDocumentTypeById(documentTypeId);
    }

    @PostMapping("/api/document-types")
    @ResponseStatus(HttpStatus.CREATED)
    public DocumentTypeMasterResponse createDocumentType(@Valid @RequestBody DocumentTypeMasterRequest request) {
        return documentTypeMasterService.createDocumentType(request);
    }

    @PutMapping("/api/document-types/{documentTypeId}")
    public DocumentTypeMasterResponse updateDocumentType(@PathVariable Long documentTypeId,
                                                         @Valid @RequestBody DocumentTypeMasterRequest request) {
        return documentTypeMasterService.updateDocumentType(documentTypeId, request);
    }

    @DeleteMapping("/api/document-types/{documentTypeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDocumentType(@PathVariable Long documentTypeId) {
        documentTypeMasterService.deleteDocumentType(documentTypeId);
    }
}
