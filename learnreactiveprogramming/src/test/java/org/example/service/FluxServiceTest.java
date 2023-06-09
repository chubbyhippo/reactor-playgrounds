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

    @Test
    void shouldReturnConcatStringFlux() {
        StepVerifier.create(service.stringConcat())
                .expectNext("a", "b", "c", "d", "e", "f")
                .verifyComplete();
    }

    @Test
    void shouldReturnConcatWithStringFlux() {
        StepVerifier.create(service.stringConcatWith())
                .expectNext("a", "b", "c", "d")
                .verifyComplete();
    }

    @Test
    void shouldReturnMergedStringFlux() {
        StepVerifier.create(service.stringMerge())
                .expectNextCount(6)
                .verifyComplete();
    }

    @Test
    void shouldReturnMergedWithStringFlux() {
        StepVerifier.create(service.stringMergeWith())
                .expectNextCount(4)
                .verifyComplete();
    }

    @Test
    void shouldReturnMergedSequentialStringFlux() {
        StepVerifier.create(service.stringMergeSequential())
                .expectNext("a", "b", "c", "d")
                .verifyComplete();
    }

    @Test
    void  shouldReturnStringZip() {
        StepVerifier.create(service.stringZip())
                .expectNext("a1", "b2")
                .verifyComplete();
    }

    @Test
    void  shouldReturnStringZipWith() {
        StepVerifier.create(service.stringZipWith())
                .expectNext("a1", "b2")
                .verifyComplete();
    }
}