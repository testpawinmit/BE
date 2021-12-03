package com.mit.pawin.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RoleDto {

    @NotNull
    private String userRoleName;

    private String parentRoleCode;

    private String userRoleCode;
}
