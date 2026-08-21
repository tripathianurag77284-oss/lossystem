CREATE TABLE document_type_master (
    document_type_id BIGSERIAL PRIMARY KEY,
    document_name VARCHAR(255) NOT NULL UNIQUE,
    category VARCHAR(100) NOT NULL,
    is_poi BOOLEAN NOT NULL DEFAULT FALSE,
    is_poa BOOLEAN NOT NULL DEFAULT FALSE,
    is_facial_document BOOLEAN NOT NULL DEFAULT FALSE,
    is_active BOOLEAN NOT NULL DEFAULT TRUE,
    created_at TIMESTAMP NOT NULL,
    modified_at TIMESTAMP NOT NULL,
    created_by BIGINT NOT NULL,
    modified_by BIGINT NOT NULL,
    is_deleted BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE TABLE documents (
    document_id BIGSERIAL PRIMARY KEY,
    applicant_id BIGINT NOT NULL,
    document_type_id BIGINT NOT NULL,
    upload_stage VARCHAR(100) NOT NULL,
    file_url VARCHAR(1000),
    verified_flag BOOLEAN NOT NULL DEFAULT FALSE,
    verification_remark VARCHAR(1000),
    verification_status VARCHAR(100),
    verification_mode VARCHAR(100),
    validated_flag BOOLEAN NOT NULL DEFAULT FALSE,
    validation_remark VARCHAR(1000),
    validation_status VARCHAR(100),
    validation_mode VARCHAR(100),
    is_active BOOLEAN NOT NULL DEFAULT TRUE,
    created_at TIMESTAMP NOT NULL,
    modified_at TIMESTAMP NOT NULL,
    verified_at TIMESTAMP,
    validated_at TIMESTAMP,
    created_by BIGINT NOT NULL,
    modified_by BIGINT NOT NULL,
    verified_by BIGINT,
    validated_by BIGINT,
    is_deleted BOOLEAN NOT NULL DEFAULT FALSE,
    CONSTRAINT fk_documents_document_type
        FOREIGN KEY (document_type_id) REFERENCES document_type_master(document_type_id)
);

CREATE INDEX idx_documents_applicant_id ON documents(applicant_id);
CREATE INDEX idx_documents_document_type_id ON documents(document_type_id);
CREATE INDEX idx_documents_is_deleted ON documents(is_deleted);
CREATE INDEX idx_document_type_master_is_deleted ON document_type_master(is_deleted);
