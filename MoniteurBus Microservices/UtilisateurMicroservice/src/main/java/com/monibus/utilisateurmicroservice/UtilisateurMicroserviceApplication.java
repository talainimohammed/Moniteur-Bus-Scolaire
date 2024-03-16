package com.monibus.utilisateurmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@EnableFeignClients(
        basePackages = "com.monibus"
)
@SpringBootApplication
public class UtilisateurMicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UtilisateurMicroserviceApplication.class, args);
    }

}