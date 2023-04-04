package org.example.service;

import reactor.core.publisher.Flux;
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

    public Flux<String> nameMonoFlatMapMany() {
        return Mono.just("Alex")
                .map(String::toUpperCase)
                .flatMapMany(s -> Flux.fromArray(s.split("")))
                .log();
    }

    public Mono<String> nameMonoZipwith() {
        var first = Mono.just("Ben");
        var second = Mono.just("Duck");

        return first.zipWith(second, (s, s2) -> s + s2);
    }
}
