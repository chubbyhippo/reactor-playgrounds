package org.example.service;

import reactor.core.publisher.Mono;

public class MonoService {
    public Mono<String> nameMono() {
        return Mono.just("Alex");
    }
}
