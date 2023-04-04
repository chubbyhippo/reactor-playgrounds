package org.example;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;
import reactor.test.StepVerifier;

import java.util.concurrent.atomic.AtomicInteger;

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

    @Test
    void shouldReplayProcessor(){

        Sinks.Many<String> sink = Sinks.many().replay().all();
        sink.tryEmitNext("a");
        sink.tryEmitNext("b");
        sink.tryEmitComplete();

        Flux<String> flux = sink.asFlux().log();

        StepVerifier.create(flux)
                .expectNext("a")
                .expectNext("b")
                .verifyComplete();

        StepVerifier.create(flux)
                .expectNext("a")
                .expectNext("b")
                .verifyComplete();
    }

    @Test
    void shouldPerformThenMany() {
        var letters = new AtomicInteger();
        var numbers = new AtomicInteger();
        var lettersPublisher = Flux.just("a", "b", "c")
                .doOnNext(value -> letters.incrementAndGet())
                .log();
        var numbersPublisher = Flux.just(1, 2, 3)
                .doOnNext(number -> numbers.incrementAndGet())
                .log();
        var thisBeforeThat = lettersPublisher.thenMany(numbersPublisher)
                .log();
        StepVerifier.create(thisBeforeThat)
                .expectNext(1, 2, 3)
                .verifyComplete();
    }

}