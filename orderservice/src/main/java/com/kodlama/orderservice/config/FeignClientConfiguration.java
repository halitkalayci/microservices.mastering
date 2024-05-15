package com.kodlama.orderservice.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.apache.http.HttpHeaders;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


@Configuration
public class FeignClientConfiguration {
    @Bean
    public RequestInterceptor requestInterceptor() {
        return new RequestInterceptor() {
            @Override
            public void apply(RequestTemplate requestTemplate) {
                // Redis
                ServletRequestAttributes attributes = (ServletRequestAttributes)
                        RequestContextHolder.getRequestAttributes();

                if(attributes != null)
                {
                    String jwtHeader = attributes.getRequest().getHeader(HttpHeaders.AUTHORIZATION);
                    requestTemplate.header(HttpHeaders.AUTHORIZATION, jwtHeader);
                }
                // Tüm feign isteklerine eklemek istediğimiz kodlar.
            }
        };
    }
}
