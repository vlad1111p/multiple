package com.multiple.multipleweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = {"com.multiple.multiplemodels.model"})
public class MultipleWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(MultipleWebApplication.class, args);
	}

}
