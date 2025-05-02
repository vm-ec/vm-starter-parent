package com.vm.ec.starter.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestClient;

import java.time.Duration;

@Configuration
public class RestClientConfig {

    @Bean
    public RestClient restClient(RestTemplateBuilder builder) {
        return RestClient.builder()
                .requestFactory(builder
                        .additionalInterceptors((request, body, execution) -> {
                            request.getHeaders().add("X-Custom-Header", "starter-lib");
                            return execution.execute(request, body);
                        })
                        .buildRequestFactory())
                .build();
    }
}
