package br.com.consigned.apigateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("client", r -> r.path("/client")
                        .filters(f -> f.rewritePath("/client", "/api/v1/client"))
                        .uri("http://localhost:8081"))
                .route("simulation", r -> r.path("/simulation/**")
                        .filters(f -> f.rewritePath("/simulation(?<simulation>/?.*)", "/api/v1/simulation${simulation}"))
                        .uri("http://localhost:8082"))
                .route("contract", r -> r.path("/contract/**")
                        .filters(f -> f.rewritePath("/contract(?<contract>/?.*)", "/api/v1/contract${contract}"))
                        .uri("http://localhost:8083"))
                .build();
    }
}
