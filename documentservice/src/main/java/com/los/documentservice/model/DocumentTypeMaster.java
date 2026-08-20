package com.los.documentservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocumentTypeMaster {

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
