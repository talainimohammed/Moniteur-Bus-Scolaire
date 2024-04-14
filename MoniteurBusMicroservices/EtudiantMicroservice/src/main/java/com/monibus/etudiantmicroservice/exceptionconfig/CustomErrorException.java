package com.monibus.etudiantmicroservice.exceptionconfig;

public class CustomErrorException extends  RuntimeException{
    public CustomErrorException(String message){
        super(message);
    }
}
