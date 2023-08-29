package com.aprendekomodo.api.health;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@Log
@RequiredArgsConstructor
public class HealthHandler {
//private  final UseCase useCase;
//private  final UseCase2 useCase2;
    public Mono<ServerResponse> getHealth(ServerRequest serverRequest) {
        ThreadContext.put("context", "HELLO");
        log.info("Salud de la App OK");
        return ServerResponse.ok().bodyValue("OK");
    }
}
