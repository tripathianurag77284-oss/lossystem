package com.los.documentservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocumentTypeMasterRequest {

    @NotBlank(message = "documentName is required")
    private String documentName;

    @NotBlank(message = "category is required")
    private String category;

    @NotNull(message = "isPoi is required")
    private Boolean isPoi;

    @NotNull(message = "isPoa is required")
    private Boolean isPoa;

    @NotNull(message = "isFacialDocument is required")
    private Boolean isFacialDocument;

    @NotNull(message = "isActive is required")
    private Boolean isActive;

    @NotNull(message = "createdBy is required")
    private Long createdBy;

    private Long modifiedBy;
}
