package com.los.customerservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OccupationResponse {

    private Long occupationId;
    private String occupationName;
    private String occupationCategory;
    private Boolean isActive;
}