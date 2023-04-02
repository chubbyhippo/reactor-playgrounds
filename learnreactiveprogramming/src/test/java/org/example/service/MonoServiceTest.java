package org.example.service;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

import java.util.List;

class MonoServiceTest {

    private final MonoService service = new MonoService();
    @Test
    void shouldReturnNameMono() {
        service.nameMono()
                .subscribe(System.out::println);

        StepVerifier
                .create(service.nameMono())
                .expectNextCount(1)
                .verifyComplete();
    }

    @Test
    void shouldReturnNameMonoFlatMap() {
       StepVerifier.create(service.nameMonoFlatMap())
               .expectNext(List.of("A", "L", "E", "X"))
               .verifyComplete();
    }

}