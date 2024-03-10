package com.monibus.notification;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "NOTIFICATION")
public interface NotificationClient {
}
