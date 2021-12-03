package com.mit.pawin.dto;

import lombok.Data;

import java.util.Date;

@Data
public class PetVaccinationDto {
    private String petVaccCode;
    private String petCode;
    private String vaccCode;
    private Date expirationDate;
}
