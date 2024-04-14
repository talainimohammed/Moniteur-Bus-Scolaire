package com.monibus.utilisateurmicroservice.exceptionconfig;

public class CustomErrorException extends  RuntimeException{
    public CustomErrorException(String message){
        super(message);
    }
}
