package com.mit.pawin.dto;


import lombok.Data;

import java.util.Date;

@Data
public class VaccinationDto {

     private String vaccCode;
     private String vaccName;
     private String petCode;
     private Date expirationDate;
     private String petVaccCode;

}
