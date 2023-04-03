package org.example;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;
import reactor.test.StepVerifier;

class ReactorTest {

    @Test
    void shouldEmitProcessor() {

        Sinks.Many<String> sink = Sinks.many().multicast().onBackpressureBuffer();
        sink.tryEmitNext("a");
        sink.tryEmitNext("b");
        sink.tryEmitComplete();

        Flux<String> flux = sink.asFlux().log();

        StepVerifier.create(flux)
                .expectNext("a")
                .expectNext("b")
                .verifyComplete();

        StepVerifier.create(flux)
                .verifyComplete();
    }

}