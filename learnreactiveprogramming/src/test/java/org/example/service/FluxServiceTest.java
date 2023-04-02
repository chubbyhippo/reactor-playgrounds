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
        StepVerifier.create(service.namesFluxMap())
                .expectNext("ALEX", "BEN", "CATHY")
                .verifyComplete();
    }

    @Test
    void shouldReturnNamesFluxMapImmutability() {
        StepVerifier.create(service.namesFluxMapImmutability())
                .expectNext("ALEX", "BEN", "CATHY")
                .verifyComplete();
    }

    @Test
    void shouldReturnNamesFluxMapFilter() {
        StepVerifier.create(service.namesFluxMapFilter())
                .expectNext("ALEX", "CATHY")
                .verifyComplete();
    }

    @Test
    void shouldReturnNamesFluxFlatMap() {
        StepVerifier.create(service.namesFluxFlatMap())
                .expectNext("A", "L", "E", "X", "B", "E", "N", "C", "A", "T", "H", "Y")
                .verifyComplete();
    }

    @Test
    void shouldReturnNamesFluxFlatMapAsync() {
        StepVerifier.create(service.namesFluxFlatMapAsync())
                .expectNextCount(12)
                .verifyComplete();
    }

    @Test
    void shouldReturnNamesFluxConcatMap() {
        StepVerifier.create(service.namesFluxConcatMap())
                .expectNext("A", "L", "E", "X", "B", "E", "N", "C", "A", "T", "H", "Y")
                .verifyComplete();
    }

    @Test
    void shouldReturnNamesFluxTransform() {
        StepVerifier.create(service.namesFluxTransform())
                .expectNext("ALEX", "BEN", "CATHY")
                .verifyComplete();
    }

    @Test
    void shouldReturnDefaultIfEmpty() {
        StepVerifier.create(service.namesFluxDefaultIfEmpty())
                .expectNext("default")
                .verifyComplete();
    }

    @Test
    void shouldReturnSwitchIfEmpty() {
        StepVerifier.create(service.namesFluxSwitchIfEmpty())
                .expectNext("default")
                .verifyComplete();
    }
}