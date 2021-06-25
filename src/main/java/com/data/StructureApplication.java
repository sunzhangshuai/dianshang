package com.data;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author sunchen
 */
@SpringBootApplication(scanBasePackages = {
        "com.data"})

@Configuration
public class StructureApplication {

    public static void main(String[] args) {
        SpringApplication.run(StructureApplication.class, args);
    }

}
