package com.mit.pawin.dto;

import lombok.Data;

import java.util.Date;

@Data
public class InjuryDto {
    private String injuryCode;
    private String petCode;
    private String injuryDes;
    private Date injuryOccurred;
    private String injuryImpact;
    private Date injuryUntil;
}
