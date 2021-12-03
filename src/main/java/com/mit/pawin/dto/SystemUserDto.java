package com.mit.pawin.dto;

import lombok.Data;

@Data
public class SystemUserDto {

    private String sysUsrCode;
    private String username;//
    private String password;
    private String empCode;
    private String appCode;
    private String userRoleCode;
}
