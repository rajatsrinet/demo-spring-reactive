package com.demospringreactive.demo_spring_reactive.restcontroller;

import java.time.Duration;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.core.io.buffer.DefaultDataBufferFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;

@RestController
public class FluxController {

    @GetMapping("/flux")
    public Flux<Integer> getIntValue(){
        System.out.println("Inside getIntValue1");
        return Flux.just(1,2,3,4)
                .delayElements(Duration.ofSeconds(1))
                .log();
    }

    @GetMapping(value = "/fluxstream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Long> getIntValueStream(){

        return Flux.interval(Duration.ofSeconds(1))
                .log();
    }

    @GetMapping(value = "/readfile", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public Flux<DataBuffer> readFile() {
        ClassPathResource resource = new ClassPathResource("sample.txt");
        return DataBufferUtils.read(resource, new DefaultDataBufferFactory(), 100);
    }
    
}
