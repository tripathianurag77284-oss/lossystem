INSERT INTO document_type_master (
    document_name, category, is_poi, is_poa, is_facial_document,
    is_active, created_at, modified_at, created_by, modified_by, is_deleted
) VALUES
    ('PAN Card', 'KYC', TRUE, FALSE, FALSE, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1001, 1001, FALSE),
    ('Aadhaar Card', 'KYC', TRUE, TRUE, TRUE, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1001, 1001, FALSE),
    ('Driving License', 'ADDRESS', TRUE, TRUE, TRUE, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1001, 1001, FALSE),
    ('Bank Statement', 'FINANCIAL', FALSE, TRUE, FALSE, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1001, 1001, FALSE),
    ('Salary Slip', 'INCOME', FALSE, FALSE, FALSE, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1001, 1001, FALSE)
ON CONFLICT (document_name) DO NOTHING;

INSERT INTO documents (
    applicant_id, document_type_id, upload_stage, file_url,
    verified_flag, verification_remark, verification_status, verification_mode,
    validated_flag, validation_remark, validation_status, validation_mode,
    is_active, created_at, modified_at, verified_at, validated_at,
    created_by, modified_by, verified_by, validated_by, is_deleted
)
SELECT
    5001, document_type_id, 'KYC', 'sample-documents/pan-card.pdf',
    FALSE, NULL, 'PENDING', 'MANUAL',
    FALSE, NULL, 'PENDING', 'MANUAL',
    TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, NULL, NULL,
    1001, 1001, NULL, NULL, FALSE
FROM document_type_master
WHERE document_name = 'PAN Card';

INSERT INTO documents (
    applicant_id, document_type_id, upload_stage, file_url,
    verified_flag, verification_remark, verification_status, verification_mode,
    validated_flag, validation_remark, validation_status, validation_mode,
    is_active, created_at, modified_at, verified_at, validated_at,
    created_by, modified_by, verified_by, validated_by, is_deleted
)
SELECT
    5002, document_type_id, 'KYC', 'sample-documents/aadhaar-card.pdf',
    TRUE, 'Verified successfully', 'VERIFIED', 'AUTO',
    TRUE, 'Validated successfully', 'VALIDATED', 'AUTO',
    TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,
    1001, 1001, 2001, 2002, FALSE
FROM document_type_master
WHERE document_name = 'Aadhaar Card';

INSERT INTO documents (
    applicant_id, document_type_id, upload_stage, file_url,
    verified_flag, verification_remark, verification_status, verification_mode,
    validated_flag, validation_remark, validation_status, validation_mode,
    is_active, created_at, modified_at, verified_at, validated_at,
    created_by, modified_by, verified_by, validated_by, is_deleted
)
SELECT
    5003, document_type_id, 'INCOME_VERIFICATION', 'sample-documents/salary-slip.pdf',
    TRUE, 'Document is readable', 'VERIFIED', 'MANUAL',
    FALSE, NULL, 'PENDING', 'MANUAL',
    TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, NULL,
    1001, 1001, 2001, NULL, FALSE
FROM document_type_master
WHERE document_name = 'Salary Slip';
