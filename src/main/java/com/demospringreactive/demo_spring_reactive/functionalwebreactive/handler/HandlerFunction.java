package com.demospringreactive.demo_spring_reactive.functionalwebreactive.handler;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Component
public class HandlerFunction {

    public Mono<ServerResponse> fluxData(ServerRequest serverRequest) {

        return ServerResponse.ok()
                .contentType(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE))
                .body(
                        Flux.interval(Duration.ofSeconds(1)).log(), Long.class
                );
    }

    public Mono<ServerResponse> monoData(ServerRequest serverRequest) {

        return ServerResponse.ok()
                .contentType(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE))
                .body(
                        Mono.just(1).log(), Integer.class
                );
    }
}
