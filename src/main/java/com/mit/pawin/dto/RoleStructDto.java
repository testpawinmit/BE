package com.mit.pawin.dto;

import lombok.Data;

import java.util.List;

@Data
public class RoleStructDto {

    private String text;
    private String value;
    private List children;

}
