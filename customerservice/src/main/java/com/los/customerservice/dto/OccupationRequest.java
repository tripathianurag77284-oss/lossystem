package com.los.customerservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OccupationRequest {

    private String occupationName;
    private String occupationCategory;
    private Boolean isActive;
}