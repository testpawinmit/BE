package com.mit.pawin.dto;


import lombok.Data;

import java.util.Date;

@Data
public class CustomerDto {

    private String custCode;
    private String custFirstName;
    private String custLastName;
    private String custEmail;
    private String custNic;
    private String custAddress;
    private String custPhone;
    private String location;
    private String petCode;
    private String petName;
    private Date dob;
    private String type;
    private String weight;
    private String gender;
    private String breedCode;
    private String colorCode;
    private String username;

}
