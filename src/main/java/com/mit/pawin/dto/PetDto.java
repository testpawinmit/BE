package com.mit.pawin.dto;

import lombok.Data;

import java.util.Date;

@Data
public class PetDto {
     private String petCode;
     private String custCode;
     private String petName;
     private Date dob;
     private String type;
     private String weight;
     private String gender;
     private String breedCode;
     private String colorCode;
}
