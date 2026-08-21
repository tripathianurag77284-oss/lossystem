package com.los.customerservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PartyRequest {

    private String firstName;
    private String middleName;
    private String lastName;

    private String fatherName;
    private String spouseName;

    private String mobile;
    private String alternateMobile;

    private String email;

    private LocalDate dob;

    private String pan;
    private String aadhar;

    private Long occupationId;
    private String otherOccupation;

    private String maritalStatus;
    private String numberOfDependent;

    private String educationType;
    private Integer education;

    private Long eduDocId;
    private Long poiDocId;
    private Long poaDocId;
    private Long photoDocId;
}