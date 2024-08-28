package org.example.finalprojectmyshop.order.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestConfig {

    @Bean
    public RestClient salesRestClient(SalesApiConfig salesApiConfig) {
        return RestClient
                .builder()
                .baseUrl(salesApiConfig.getBaseUrl())
                .build();
    }

}
