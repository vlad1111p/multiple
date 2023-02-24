package com.multiple.multipleauthorizationserver.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;

public interface SecurityService {
    @Bean
    ClientRegistrationRepository clientRegistrationRepository();

    @Bean
    OAuth2AuthorizedClientService authorizedClientService();
}
