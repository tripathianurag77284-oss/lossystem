package com.los.documentservice.exception;

public class DocumentNotFoundException extends RuntimeException {

    public DocumentNotFoundException(Long id) {
        super("Document not found with id: " + id);
    }

    public DocumentNotFoundException(String resource, Long id) {
        super(resource + " not found with id: " + id);
    }
}
