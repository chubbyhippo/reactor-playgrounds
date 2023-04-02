package org.example.service;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

class FluxServiceTest {

    @Test
    void shouldReturnNames() {
        var service = new FluxService();
        service.namesFlux()
                .subscribe(System.out::println);

        StepVerifier
                .create(service.namesFlux())
                .expectNextCount(3)
                .verifyComplete();
    }

}