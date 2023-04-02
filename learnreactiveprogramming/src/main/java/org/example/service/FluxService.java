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
}
