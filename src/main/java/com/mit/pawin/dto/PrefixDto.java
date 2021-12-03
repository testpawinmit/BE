package com.mit.pawin.dto;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Data
@PropertySource("classpath:util.properties")
@ConfigurationProperties(prefix="file")
@Component
public class PrefixDto {
    private String profileImg;
    private String transLog;
}
