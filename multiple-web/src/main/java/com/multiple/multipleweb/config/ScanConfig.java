package com.multiple.multipleweb.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = {"com.multiple.multiplemodels.model"})
@EnableJpaRepositories(basePackages = "com.multiple.multiplemodels.repository",
        entityManagerFactoryRef = "entityManagerFactory",
        transactionManagerRef = "transactionManager")
@ComponentScan("com.multiple.multipleauthorizationserver.auth")

public class ScanConfig {
}
