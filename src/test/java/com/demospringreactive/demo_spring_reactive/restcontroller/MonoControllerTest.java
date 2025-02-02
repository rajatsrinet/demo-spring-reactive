package com.demospringreactive.demo_spring_reactive.restcontroller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.List;

@ExtendWith(SpringExtension.class)
@WebFluxTest
public class MonoControllerTest {
    @Autowired
    WebTestClient webTestClient;

    @Test
    public void getIntValueTest() {
        Flux<Integer> responseBody = webTestClient.get()
                .uri("/mono")
                .accept(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE))
                .exchange()
                .expectStatus().isOk()
                .returnResult(Integer.class)
                .getResponseBody();
        StepVerifier.create(responseBody.log())
                .expectSubscription()
                .expectNext(1)
                .verifyComplete();

    }

    @Test
    public void getIntValueTest3() {
        List<Integer> expectIntegerList = Arrays.asList(1);
        EntityExchangeResult<List<Integer>> listEntityExchangeResult = webTestClient.get()
                .uri("/mono")
                .accept(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE))
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Integer.class)
                .returnResult();
        Assertions.assertEquals(expectIntegerList, listEntityExchangeResult.getResponseBody());
    }
}
