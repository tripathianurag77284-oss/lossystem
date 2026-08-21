package com.los.customerservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RelationshipTypeRequest {

    private String relationshipType;
    private String relationshipDescription;
}