package com.aprendekomodo.api.health;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterHealthRest {
    @Bean
    public RouterFunction<ServerResponse> routerFunction(HealthHandler healthHandler) {
        return route(GET("/api/health"), healthHandler::getHealth);
    }
}
