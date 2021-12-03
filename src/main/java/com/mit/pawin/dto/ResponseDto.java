package com.mit.pawin.dto;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Data
@PropertySource("classpath:response.properties")
@ConfigurationProperties(prefix="response")
@Component
public class ResponseDto {
    private String ok;
    private String fail;
    private String error;
    private String tokenExpir;
    private String tokenExpirInactive;
    private String errorMessage;
    private String used;
}
