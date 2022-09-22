package com.lastcivilization.statswriteservice.infrastructure.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages = "com.lastcivilization.statswriteservice.infrastructure.service")
public class StatsWriteServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(StatsWriteServiceApplication.class, args);
    }

}
