package com.mit.pawin.dto;

import lombok.Data;

import java.util.Date;

@Data
public class AppointmentDto {

    private String appId;
    private String appCode;
    private String serviceCatCode;
    private String serviceCode;
    private String roomCode;
    private String empCode;
    private Date checkInDate;
    private String checkInTime;
    private Date checkOutDate;
    private String checkOutTime;
    private Object petCodes;
    private String totalPrice;
    private String paymentType;

}
