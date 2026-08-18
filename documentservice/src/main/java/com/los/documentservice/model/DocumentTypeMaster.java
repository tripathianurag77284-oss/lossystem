package com.los.documentservice.model;

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
@Table(name = "document_types")
public class DocumentTypeMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long documentTypeId;
    private String documentName;
    private String category;
    private Boolean isPoi;
    private Boolean isPoa;
    private Boolean isFacialDocument;
    private Boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private Long createdBy;
    private Long modifiedBy;
    private Boolean isDeleted;
}
