package com.mit.pawin.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class ValuePassingDto {

    private String clientIp;
    private String username;
}
