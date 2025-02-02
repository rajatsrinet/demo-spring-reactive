package com.demospringreactive.demo_spring_reactive.restcontroller;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class MonoTest {

    @Test
    public void monoTest(){
        Mono<String> stringMono = Mono.just("Spring");
        StepVerifier.create(stringMono.log())
                .expectNext("Spring")
                .verifyComplete()
                ;
    }

    @Test
    public void testFlux_WithError() {
        StepVerifier.create(Mono.error(new RuntimeException("exception occurred")))
                .expectError()
                .verify();
    }
}
