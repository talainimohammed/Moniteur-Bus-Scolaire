package com.monibus.notificationmicroservice.service;

public interface INotification {

    public void sendNotification(String message);
    public void receiveNotification(String message);
    
}
