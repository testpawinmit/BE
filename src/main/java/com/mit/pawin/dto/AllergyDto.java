package com.mit.pawin.dto;

import lombok.Data;

@Data
public class AllergyDto {
    private String allergyCode;
    private String petCode;
    private String allergyName;
    private String allergyTreatment;

}
