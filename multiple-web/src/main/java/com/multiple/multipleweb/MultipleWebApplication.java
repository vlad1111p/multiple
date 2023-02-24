package com.multiple.multipleweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.multiple.multiplemodels", "com.multiple.multipleweb",
        "com.multiple.multipleauthorizationserver"})
@EnableAutoConfiguration
public class MultipleWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(MultipleWebApplication.class, args);
    }

}
