package com.example.apigateway.config;

import com.example.apigateway.filter.AuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@Configuration
@EnableHystrix
public class GatewayConfig {
    @Autowired
    private AuthenticationFilter filter;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("eclient", r -> r.path("/eclient/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://ECLIENT"))
                .route("service", r -> r.path("/auth/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://SERVICE"))
                .build();
    }
}
