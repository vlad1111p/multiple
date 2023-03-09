package com.multiple.multipleweb.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@EntityScan(basePackages = {"com.multiple.multiplemodels.model"})
@EnableJpaRepositories(basePackages = "com.multiple.multiplemodels.repository")
@ComponentScan("com.multiple.multipleauthorizationserver.auth")
@SpringBootApplication(scanBasePackages = {"com.multiple.multiplemodels", "com.multiple.multipleweb",
        "com.multiple.multipleauthorizationserver"})
@EnableAutoConfiguration
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface SpringBootApplicationCustom {
}
