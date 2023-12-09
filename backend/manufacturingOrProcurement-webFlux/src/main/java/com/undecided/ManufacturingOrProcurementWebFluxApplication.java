package com.undecided;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class InventoryWebFluxApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryWebFluxApplication.class, args);
    }

}
