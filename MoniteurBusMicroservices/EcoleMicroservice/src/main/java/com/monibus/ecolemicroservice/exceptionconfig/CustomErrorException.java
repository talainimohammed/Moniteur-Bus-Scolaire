package com.monibus.ecolemicroservice.exceptionconfig;

public class CustomErrorException extends  RuntimeException{
    public CustomErrorException(String message){
        super(message);
    }
}
