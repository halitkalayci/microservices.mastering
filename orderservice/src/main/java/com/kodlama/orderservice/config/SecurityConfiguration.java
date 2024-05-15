package com.kodlama.orderservice.config;

import com.kodlamaio.core.config.BaseSecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final BaseSecurityService baseSecurityService;

    private static String[] WHITE_LIST = {
            "/swagger-ui/**",
            "/v2/api-docs",
            "/v3/api-docs",
            "/v3/api-docs/**"
    };

    @Bean
    public SecurityFilterChain httpSecurity(HttpSecurity http) throws Exception
    {
        baseSecurityService.configureCommonSecurity(http);

        http

                .authorizeHttpRequests((req)->
                        req.requestMatchers(WHITE_LIST).permitAll()
                                .anyRequest().permitAll());

        return http.build();
    }
}
