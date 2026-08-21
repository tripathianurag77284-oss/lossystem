package com.los.documentservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "document_type_master")
public class DocumentTypeMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "document_type_id")
    private Long documentTypeId;
    @Column(name = "document_name", nullable = false, unique = true, length = 255)
    private String documentName;
    @Column(nullable = false, length = 100)
    private String category;
    @Column(name = "is_poi", nullable = false)
    private Boolean isPoi;
    @Column(name = "is_poa", nullable = false)
    private Boolean isPoa;
    @Column(name = "is_facial_document", nullable = false)
    private Boolean isFacialDocument;
    @Column(name = "is_active", nullable = false)
    private Boolean isActive;
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    @Column(name = "modified_at", nullable = false)
    private LocalDateTime modifiedAt;
    @Column(name = "created_by", nullable = false)
    private Long createdBy;
    @Column(name = "modified_by", nullable = false)
    private Long modifiedBy;
    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted;
}
