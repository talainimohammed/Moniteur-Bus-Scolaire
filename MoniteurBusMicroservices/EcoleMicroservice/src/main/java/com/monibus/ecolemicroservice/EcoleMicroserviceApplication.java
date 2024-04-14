package com.monibus.ecolemicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@EnableFeignClients(
        basePackages = "com.monibus"
)
@SpringBootApplication
public class EcoleMicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EcoleMicroserviceApplication.class, args);
    }

}
