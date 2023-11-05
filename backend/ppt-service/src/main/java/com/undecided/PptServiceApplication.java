package com.undecided;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class PptServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PptServiceApplication.class, args);
    }

}
