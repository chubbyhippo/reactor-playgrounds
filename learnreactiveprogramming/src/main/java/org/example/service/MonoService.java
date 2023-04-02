package org.example.service;

import reactor.core.publisher.Mono;

import java.util.List;

public class MonoService {
    public Mono<String> nameMono() {
        return Mono.just("Alex").log();
    }

    public Mono<List<String>> nameMonoFlatMap() {
        return Mono.just("Alex")
                .map(String::toUpperCase)
                .flatMap(s -> Mono.just(List.of(s.split(""))))
                .log();
    }
}
