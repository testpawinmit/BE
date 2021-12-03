package com.mit.pawin.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserWithPrivilegeDto {

    private String userRoleCode;
    private String userPrivilegeCode;
    private List<String> userPrivilegeCodeList;
}
