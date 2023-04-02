package org.example.service;

import reactor.core.publisher.Flux;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.Duration;
import java.util.List;

public class FluxService {
    private final List<String> names = List.of("Alex", "Ben", "Cathy");

    public Flux<String> namesFlux() {
        return Flux.fromIterable(names).log();
    }

    public Flux<String> namesFluxMap() {
        return Flux.fromIterable(names)
                .map(String::toUpperCase)
                .log();
    }

    public Flux<String> namesFluxMapImmutability() {

        Flux<String> stringFlux = Flux.fromIterable(names);
//        stringFlux.map(String::toUpperCase); // immutability

        return stringFlux.map(String::toUpperCase).log();
    }

    public Flux<String> namesFluxMapFilter() {
        return Flux.fromIterable(names)
                .filter(name -> name.length() > 3)
                .map(String::toUpperCase)
                .log();
    }


    public Flux<String> namesFluxFlatMap() {
        return Flux.fromIterable(names)
                .map(String::toUpperCase)
                .flatMap(s -> Flux.fromArray(s.split("")))
                .log();
    }

    public Flux<String> namesFluxFlatMapAsync() {
        return Flux.fromIterable(names)
                .map(String::toUpperCase)
                .flatMap(s -> {
                    try {
                        return Flux.fromArray(s.split(""))
                                .delayElements(Duration.ofMillis(
                                        SecureRandom.getInstanceStrong()
                                                .nextInt(1000)));
                    } catch (NoSuchAlgorithmException e) {
                        return Flux.error(new RuntimeException(e));
                    }
                })
                .log();
    }

    public Flux<String> namesFluxConcatMap() {
        return Flux.fromIterable(names)
                .map(String::toUpperCase)
                .concatMap(s -> Flux.fromArray(s.split(""))
                        .delayElements(Duration.ofMillis(1000)))
                .log();
    }

    public Flux<String> namesFluxTransform() {
        return Flux.fromIterable(names)
                .transform(stringFlux -> stringFlux.map(String::toUpperCase))
                .log();
    }
}
