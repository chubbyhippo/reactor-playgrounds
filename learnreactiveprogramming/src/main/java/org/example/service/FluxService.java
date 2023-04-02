package org.example.service;

import reactor.core.publisher.Flux;

import java.util.List;

public class FluxService {
    public Flux<String> namesFlux() {
        return Flux.fromIterable(List.of("Alex", "Ben", "Cathy")).log();
    }

    public Flux<String> namesFluxMap() {
        return Flux.fromIterable(List.of("Alex", "Ben", "Cathy"))
                .map(String::toUpperCase)
                .log();
    }

    public Flux<String> namesFluxMapImmutability() {

        Flux<String> stringFlux = Flux.fromIterable(List.of("Alex", "Ben", "Cathy"));
//        stringFlux.map(String::toUpperCase); // immutability

        return stringFlux.map(String::toUpperCase).log();
    }
}
