package com.demospringreactive.demo_spring_reactive.restcontroller;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class FluxTest {

    @Test
    public void testFlux() {

        Flux<String> stringFlux = Flux.just("Spring", "Spring boot", "Spring boot reactive")
                .concatWith(Flux.error(new RuntimeException("exception occurred")));
        stringFlux.log().subscribe(System.out::println
                , (e) -> System.err.println(e)
        , ()-> System.out.println("Completed"));
    }

    @Test
    public void testFlux_WithSuccess() {

        Flux<String> stringFlux = Flux.just("Spring", "Spring boot", "Spring boot reactive")
                //.concatWith(Flux.error(new RuntimeException("exception occurred")))
                .log();
        StepVerifier.create(stringFlux)
                .expectNext("Spring")
                .expectNext("Spring boot")
                .expectNext( "Spring boot reactive")
                .verifyComplete();
    }

    @Test
    public void testFlux_WithError() {

        Flux<String> stringFlux = Flux.just("Spring", "Spring boot", "Spring boot reactive")
                .concatWith(Flux.error(new RuntimeException("exception occurred")))
                .log();
        StepVerifier.create(stringFlux)
                .expectNext("Spring", "Spring boot", "Spring boot reactive")
                .expectError(RuntimeException.class)
                //.expectErrorMessage("exception occurred")
                .verify();
    }

    @Test
    public void testFlux_ElemenCount() {

        Flux<String> stringFlux = Flux.just("Spring", "Spring boot", "Spring boot reactive")
                //.concatWith(Flux.error(new RuntimeException("exception occurred")))
                .log();
        StepVerifier.create(stringFlux)
                .expectNextCount(3)
                .verifyComplete();
    }

    @Test
    public void testFlux_Zip() {
        String s = new String("abc");
        Flux.zip(Flux.just("1"),
                Flux.just("1", "2", "3", "4"),
                Flux.just("1", "2", "3", "4"),
                Flux.just("1", "2", "3", "4"),
                Flux.just("1", "2", "3", "4"));
    }
}
