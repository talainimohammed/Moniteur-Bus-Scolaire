package com.monibus.notificationmicroservice.exceptionconfig;

public class CustomErrorException extends  RuntimeException{
    public CustomErrorException(String message){
        super(message);
    }
}
