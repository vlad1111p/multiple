package com.multiple.multipleweb;

import com.multiple.multipleweb.config.SpringBootApplicationCustom;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplicationCustom
public class MultipleWebApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(MultipleWebApplication.class, args);
    }
}

