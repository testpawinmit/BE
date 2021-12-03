package com.mit.pawin.dto;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Data
@PropertySource("classpath:table.properties")
@ConfigurationProperties(prefix="")
@Component
public class TableDto {
    private String Company;
    private String Branch;
    private String Department;
    private String Section;
    private String Grade;
    private String Egroup;
    private String JobTitle;
    private String Nationality;
    private String Religion;
}
