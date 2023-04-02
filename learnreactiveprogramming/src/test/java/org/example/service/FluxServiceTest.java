package org.example.service;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

class FluxServiceTest {

    private final FluxService service = new FluxService();
    @Test
    void shouldReturnNames() {
        service.namesFlux()
                .subscribe(System.out::println);

        StepVerifier
                .create(service.namesFlux())
                .expectNextCount(3)
                .verifyComplete();
    }

    @Test
    void shouldReturnUppercaseNames() {
         StepVerifier.create(service.namesFluxMap() )
                 .expectNext("ALEX", "BEN", "CATHY")
                 .verifyComplete();
    }

    @Test
    void shouldReturnNamesFluxMapImmutability() {
        StepVerifier.create(service.namesFluxMapImmutability() )
                .expectNext("ALEX", "BEN", "CATHY")
                .verifyComplete();
    }
}