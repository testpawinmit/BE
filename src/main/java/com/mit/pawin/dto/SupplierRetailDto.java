package com.mit.pawin.dto;

import lombok.Data;

@Data
public class SupplierRetailDto {

    private String suppRetailId;
    private String suppCode;
    private String retailCode;
    private String retailName;
    private String maxCount;
    private String wholeSalePrice;
}
