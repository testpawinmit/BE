package com.mit.pawin.dto;


import lombok.Data;

import java.util.Date;

@Data
public class MedicationDto {
     private String medCode;
     private String petCode;
     private String medDosage;
     private String medName;
     private String medFrequency;
     private String medAM;
     private String medMid;
     private String medPM;
     private Date medUntil;
}
