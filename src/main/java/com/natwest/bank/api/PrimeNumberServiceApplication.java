package com.natwest.bank.api;

import com.natwest.bank.api.config.CacheProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * Application Entry point.
 */
@SpringBootApplication
@EnableConfigurationProperties(CacheProperties.class)
public class PrimeNumberServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PrimeNumberServiceApplication.class, args);
    }

}
