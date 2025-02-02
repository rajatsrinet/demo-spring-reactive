package com.demospringreactive.demo_spring_reactive.functionalwebreactive.router;

import com.demospringreactive.demo_spring_reactive.functionalwebreactive.handler.HandlerFunction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration
public class RouterFunctionConfig {

    @Bean
    public RouterFunction<ServerResponse> route(HandlerFunction handlerFunction) {
        return RouterFunctions
                .route(GET("/function/flux").and(accept(MediaType.APPLICATION_JSON)), handlerFunction::fluxData)
                .andRoute(GET("/function/mono").and(accept(MediaType.APPLICATION_JSON)), handlerFunction::monoData);
    }
}
