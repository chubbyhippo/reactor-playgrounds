package org.example.service;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

class MonoServiceTest {

    @Test
    void shouldReturnNameMono() {
        var service = new MonoService();
        service.nameMono()
                .subscribe(System.out::println);

        StepVerifier
                .create(service.nameMono())
                .expectNextCount(1)
                .verifyComplete();
    }

}