package com.mit.pawin.dto;

import lombok.Data;

@Data
public class DietaryDto {
     private String dietCode;
     private String petCode;
     private String ownFood;
     private String feedTime;
     private String feedAlone;
     private Object feedingTimeMap;
     private String feedType;
}
