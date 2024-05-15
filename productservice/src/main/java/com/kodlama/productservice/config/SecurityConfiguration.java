package com.kodlama.productservice.config;

import com.kodlamaio.core.config.BaseSecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final BaseSecurityService baseSecurityService;

    private static String[] WHITE_LIST = {
            "/swagger-ui/**",
            "/api/v1/auth/login",
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
                        .anyRequest().authenticated());

        return http.build();
    }
}
