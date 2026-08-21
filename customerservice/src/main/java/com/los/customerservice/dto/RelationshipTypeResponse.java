package com.los.customerservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RelationshipTypeResponse {

    private Long relationshipTypeId;
    private String relationshipType;
    private String relationshipDescription;
}