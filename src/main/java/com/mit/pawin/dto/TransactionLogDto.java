package com.mit.pawin.dto;

import lombok.Data;

@Data
public class TransactionLogDto {

    private String username;
    private String action;
    private String function;
    private String dateTime;
    private String ip;
    private int id;
}
