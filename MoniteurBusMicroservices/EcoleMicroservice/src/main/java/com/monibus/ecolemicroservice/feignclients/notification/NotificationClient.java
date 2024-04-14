package com.monibus.ecolemicroservice.feignclients.notification;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "NOTIFICATION")
public interface NotificationClient {
}
