package com.mit.pawin.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ServiceDogDto {
    private String serviceDogId;
    private String serviceDogCode;
    private String serviceDogName;
    private Date serviceDogDob;
    private String breedCode;
    private String colorCode;
    private String note;
}
