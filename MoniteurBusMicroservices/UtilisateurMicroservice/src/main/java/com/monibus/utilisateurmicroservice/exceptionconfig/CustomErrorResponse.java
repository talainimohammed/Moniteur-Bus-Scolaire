package com.monibus.utilisateurmicroservice.exceptionconfig;

import lombok.Data;

@Data
public class CustomErrorResponse {
    private int status;
    private  String message;
    private Long timeStamp;

}
