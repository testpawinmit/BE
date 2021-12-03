package com.mit.pawin.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class EmployeeDto {

    private String empCode;
    private String empFirstName;
    private String empLastName;
    private String empFullName;
    private String empEmail;
    private String empNo;
    private String empSex;
    private String empCivilStatus;
    private Date dateOfBirth;
    private String empNic;
    private String epfNo;
    private Date dateOfJoin;
    private String machineStatus;
    private String machineCode;
    private Date confirmDate;
    private String empStatus;
    private Date dateLeft;
    private String addrLine1;
    private String addrLine2;
    private String city;
    private String postalCode;
    private String telResidence;
    private String telMobile;
    private String bankAcntNo;
    private String empCompany;
    private String empBranch;
    private String empDepartment;
    private String empSection;
    private String empGrade;
    private String empGroup;
    private String empJobTitle;
    private String empNationality;
    private String empReligion;
    private String fixedShift;
    private String shiftGroup;
    private String calendarTypeCode;
    private String otType;
    private String leaveType;
    private List<Object> qualifications;
    private List<Object> familyDetails;
    private List<Object> dynamicFields;
    private String title;
    private String empConfirmation;
    private Date empFutureConfirmDate;
    private String immediateBossCode;
    private String empName;
    private String empPhone;
    private String empAddress;

}
