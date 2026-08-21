package com.los.customerservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DocumentTypeResponse {

    private Long documentTypeId;
    private String documentName;
    private String category;
    private Boolean isPoi;
    private Boolean isPoa;
    private Boolean isFacialDocument;
    private Boolean isActive;
}