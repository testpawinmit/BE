package com.mit.pawin.dto;

import lombok.Data;

@Data
public class ResourcesDto {
    private String roomId;
    private String roomCode;
    private String roomName;
    private String maxWeight;
    private String cleanNeeded;
}
