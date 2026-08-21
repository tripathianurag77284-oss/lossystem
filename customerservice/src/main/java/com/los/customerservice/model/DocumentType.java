package com.los.customerservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "document_type")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocumentType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "document_type_id")
    private Long documentTypeId;

    @Column(name = "document_name")
    private String documentName;

    @Column(name = "category")
    private String category;

    @Column(name = "is_poi")
    private Boolean isPoi;

    @Column(name = "is_poa")
    private Boolean isPoa;

    @Column(name = "is_facial_document")
    private Boolean isFacialDocument;

    @Column(name = "is_active")
    private Boolean isActive;
}