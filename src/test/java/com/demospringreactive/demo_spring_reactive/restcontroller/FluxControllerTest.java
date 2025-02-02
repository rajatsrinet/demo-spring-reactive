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

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

@ExtendWith(SpringExtension.class)
@WebFluxTest
public class FluxControllerTest {

    @Autowired
    WebTestClient webTestClient;

    @Test
    public void getIntValueTest() {
        Flux<Integer> responseBody = webTestClient.get()
                .uri("/flux")
                .accept(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE))
                .exchange()
                .expectStatus().isOk()
                .returnResult(Integer.class)
                .getResponseBody();
        StepVerifier.create(responseBody.log())
                .expectSubscription()
                .expectNext(1, 2, 3, 4)
                .verifyComplete();

    }

    @Test
    public void getIntValueTest1() {
        webTestClient.get()
                .uri("/flux")
                .accept(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE))
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Integer.class)
                .hasSize(4);


    }

    @Test
    public void getIntValueTest2() {
        List<Integer> expectIntegerList = Arrays.asList(1, 2, 3, 4);
        webTestClient.get()
                .uri("/flux")
                .accept(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE))
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Integer.class)
                .consumeWith((response) -> {
                    Assertions.assertEquals(expectIntegerList, response.getResponseBody());
                });
    }

    @Test
    public void getIntValueTest3() {
        List<Integer> expectIntegerList = Arrays.asList(1, 2, 3, 4);
        EntityExchangeResult<List<Integer>> listEntityExchangeResult = webTestClient.get()
                .uri("/flux")
                .accept(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE))
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Integer.class)
                .returnResult();
        Assertions.assertEquals(expectIntegerList, listEntityExchangeResult.getResponseBody());
    }

    @Test
    public void getIntValueTest4() throws InterruptedException {
        Flux<Long> responseBody = webTestClient.get()
                .uri("/fluxstream")
                .accept(MediaType.valueOf(MediaType.TEXT_EVENT_STREAM_VALUE))
                .exchange()
                .expectStatus().isOk()
                .returnResult(Long.class)
                .getResponseBody();
        responseBody.subscribe(e -> System.out.println("value" + e));
        Thread.sleep(1000L);
        /*StepVerifier.create(responseBody.log())
                .expectSubscription()
                .expectNext(0l, 1l, 2l, 3l, 4l)
                .thenCancel()
                .verify();*/


    }
}
