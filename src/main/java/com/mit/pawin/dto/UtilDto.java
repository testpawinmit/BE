package com.mit.pawin.dto;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Data
@PropertySource("classpath:util.properties")
@ConfigurationProperties(prefix="code")
@Component
public class UtilDto {
    private String emp;
    private String qualification;
    private String family;
    private String empDynamic;
    private String email;
    private String role;
    private String sysUsr;
    private String userPriv;
    private String userWithPriv;
    private String jobTitle;
    private String company;
    private String attr;
    private String dept;
    private String branch;
    private String section;
    private String grade;
    private String egroup;
    private String nationality;
    private String religion;
    private String anCal;
    private String anCalType;
    private String anCalLeave;
    private String anCalGen;
    private String acwe;
    private String lt;
    private String calAsign;
    private String fsm;
    private String fswe;
    private String sg;
    private String shift;
    private String ar;
}
