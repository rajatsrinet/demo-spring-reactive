package com.demospringreactive.demo_spring_reactive.restcontroller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
public class MonoController {

    @GetMapping("/mono")
    public Mono<Integer> getIntValue() {
        System.out.println("system out print ln"+1);
        System.out.println("system out print ln"+2);
        System.out.println("system out print ln"+3);
        System.out.println("system out print ln"+4);
        System.out.println("system out print ln"+5);
        return Mono.just(1)
                .log();
    }
}
