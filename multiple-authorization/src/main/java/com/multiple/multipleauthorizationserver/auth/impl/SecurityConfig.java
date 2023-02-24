package com.multiple.multipleauthorizationserver.auth.impl;

import com.multiple.multipleauthorizationserver.auth.SecurityService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private final SecurityService securityService;

    public SecurityConfig(SecurityService securityService) {
        this.securityService = securityService;
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/api/login", "/api/login_JWT", "/api/oauth_login").permitAll()
                .anyRequest().authenticated()
                .and()
                .oauth2Login()
                .loginPage("/api/login")
                .clientRegistrationRepository(securityService.clientRegistrationRepository())
                .authorizedClientService(securityService.authorizedClientService());
        return http.build();
    }
}