package com.mit.pawin.dto;


import lombok.Data;

@Data
public class EmployeeScheduleDto {

    private String empCode;
    private String day;
    private String startTime;
    private String EndTime;

    private String isSunday;
    private String isMonday;
    private String isTuesday;
    private String isWednesday;
    private String isThursday;
    private String isFriday;
    private String isSaturday;

    private String sunStartTime;
    private String sunEndTime;
    private String monStartTime;
    private String monEndTime;
    private String tueStartTime;
    private String tueEndTime;
    private String wedStartTime;
    private String wedEndTime;
    private String thuStartTime;
    private String thuEndTime;
    private String friStartTime;
    private String friEndTime;
    private String satStartTime;
    private String satEndTime;
}
