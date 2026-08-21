package com.los.documentservice.controller;

import com.los.documentservice.dto.DocumentRequest;
import com.los.documentservice.dto.DocumentResponse;
import com.los.documentservice.dto.DocumentTypeMasterRequest;
import com.los.documentservice.dto.DocumentTypeMasterResponse;
import com.los.documentservice.service.DocumentService;
import com.los.documentservice.service.DocumentTypeMasterService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tools.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Set;

@RestController
public class DocumentController {

    private final DocumentService documentService;
    private final DocumentTypeMasterService documentTypeMasterService;
    private final ObjectMapper objectMapper;
    private final Validator validator;

    public DocumentController(DocumentService documentService,
                              DocumentTypeMasterService documentTypeMasterService,
                              ObjectMapper objectMapper,
                              Validator validator) {
        this.documentService = documentService;
        this.documentTypeMasterService = documentTypeMasterService;
        this.objectMapper = objectMapper;
        this.validator = validator;
    }

    @GetMapping("/")
    public String home() {
        return "redirect:/swagger-ui.html";
    }

    @GetMapping("/api/documents")
    public List<DocumentResponse> getAllDocuments() {
        return documentService.getAllDocuments();
    }

    @GetMapping("/api/documents/{documentId}")
    public DocumentResponse getDocumentById(@PathVariable Long documentId) {
        return documentService.getDocumentById(documentId);
    }

    @PostMapping(value = "/api/documents", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public DocumentResponse createDocument(@RequestPart("document") String documentJson,
                                           @RequestPart("file") MultipartFile file) {
        return documentService.createDocument(parseDocumentRequest(documentJson), file);
    }

    @PutMapping(value = "/api/documents/{documentId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public DocumentResponse updateDocument(@PathVariable Long documentId,
                                           @RequestPart("document") String documentJson,
                                           @RequestPart("file") MultipartFile file) {
        return documentService.updateDocument(documentId, parseDocumentRequest(documentJson), file);
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

    private DocumentRequest parseDocumentRequest(String documentJson) {
        try {
            DocumentRequest request = objectMapper.readValue(documentJson, DocumentRequest.class);
            Set<ConstraintViolation<DocumentRequest>> violations = validator.validate(request);
            if (!violations.isEmpty()) {
                throw new ConstraintViolationException(violations);
            }
            return request;
        } catch (ConstraintViolationException exception) {
            throw exception;
        } catch (Exception exception) {
            throw new IllegalArgumentException("The document form field must contain valid JSON", exception);
        }
    }
}
