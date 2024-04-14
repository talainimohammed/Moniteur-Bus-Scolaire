package com.monibus.realtimemicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@EnableFeignClients(
        basePackages = "com.monibus"
)
@SpringBootApplication
public class RealTimeMicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RealTimeMicroserviceApplication.class, args);
    }

}
